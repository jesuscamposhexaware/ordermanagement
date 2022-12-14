package com.jcampos.ordermanagement.service.impl;

import java.text.MessageFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import com.jcampos.ordermanagement.constant.Constants;
import com.jcampos.ordermanagement.constant.ErrorMessage;
import com.jcampos.ordermanagement.converter.DtoToOrderConverter;
import com.jcampos.ordermanagement.converter.DtoToOrderDetailConverter;
import com.jcampos.ordermanagement.converter.OrderToDtoConverter;
import com.jcampos.ordermanagement.domain.Order;
import com.jcampos.ordermanagement.domain.OrderDetail;
import com.jcampos.ordermanagement.domain.OrderDetailKey;
import com.jcampos.ordermanagement.domain.Product;
import com.jcampos.ordermanagement.domain.User;
import com.jcampos.ordermanagement.dto.OrderDetailDto;
import com.jcampos.ordermanagement.dto.OrderDto;
import com.jcampos.ordermanagement.repository.OrderDetailRepository;
import com.jcampos.ordermanagement.repository.OrderRepository;
import com.jcampos.ordermanagement.repository.ProductRepository;
import com.jcampos.ordermanagement.repository.UserRepository;
import com.jcampos.ordermanagement.service.OrderService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderToDtoConverter orderToDtoConverter;
	
	@Autowired
	private DtoToOrderConverter dtoToOrderConverter;
	
	@Autowired
	private DtoToOrderDetailConverter dtoToOrderDetailConverter;

	@Override
	public OrderDto getOrderById(Long id) {
		Optional<Order> order = orderRepository.findById(id);
		
		if(!order.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					MessageFormat.format(ErrorMessage.ORDER_NOT_FOUND, String.valueOf(id)));
		
		order.get().setOrderDetails(orderDetailRepository.findByOrderId(order.get().getIdOrder()));
		
		return orderToDtoConverter.convert(order.get());
	}
	
	@Override
	public List<OrderDto> getOrdersByUserId(Long idUser) {
		List<Order> orders = orderRepository.findByUserIdUser(idUser);

		orders.stream().forEach(o -> {
			o.setOrderDetails(orderDetailRepository.findByOrderId(o.getIdOrder()));
		});
		
		return orders.stream()
				.map(o -> orderToDtoConverter.convert(o))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public OrderDto createOrder(OrderDto orderDto) {
		
		Order order = dtoToOrderConverter.convert(orderDto);
		
		Optional<User> user = userRepository.findById(orderDto.getIdUser());
		
		if(!user.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					MessageFormat.format(ErrorMessage.USER_NOT_FOUND, String.valueOf(orderDto.getIdUser())));
		
		order.setUser(user.get());
		order.setStatus(Constants.ORDER_STATUS_IN_PROGRESS);
		order.setCreatedAt(Instant.now());
		
		// Save order and generate id order
		order = orderRepository.save(order);
		
		List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
		Set<Product> productsToUpdate = new HashSet<Product>();
		Set<Long> productsIds = new HashSet<Long>();
		
		for (OrderDetailDto dto : orderDto.getOrderDetails()) {
			OrderDetail orderDetail = dtoToOrderDetailConverter.convert(dto);
			Optional<Product> productO = productRepository.findById(dto.getIdProduct());
			
			// Validate if the product is eligible to order
			Product product = validateProductForOrder(productO, dto, productsIds);
			productsToUpdate.add(product);
			
			// Assign key to order detail after passing all the validations
			orderDetail.setKey(new OrderDetailKey(order, product));
			
			orderDetails.add(orderDetail);
		}
		
		// Insert details for the order
		orderDetails = orderDetailRepository.saveAll(orderDetails);
		
		// Update stock of ordered products
		productRepository.saveAll(productsToUpdate);
		
		order.setOrderDetails(orderDetails);
		
		return orderToDtoConverter.convert(order);
		
	}

	@Override
	@Transactional
	public void deleteOrder(Long idOrder) {
		
		Optional<Order> order = orderRepository.findById(idOrder);
	
		if(order.isPresent()) {
			orderDetailRepository.deleteAll(orderDetailRepository.findByOrderId(order.get().getIdOrder()));
			orderRepository.delete(order.get());
		}
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					MessageFormat.format(ErrorMessage.ORDER_NOT_FOUND, idOrder));
		
	}

	@Override
	@Transactional
	public OrderDto updateOrder(Long id, OrderDto orderDto) {
		
		if(!orderRepository.existsById(id))
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					MessageFormat.format(ErrorMessage.ORDER_NOT_FOUND, id));
		
		Optional<User> user = userRepository.findById(orderDto.getIdUser());
		
		if(!user.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					MessageFormat.format(ErrorMessage.USER_NOT_FOUND, String.valueOf(orderDto.getIdUser())));
		
		Order order = dtoToOrderConverter.convert(orderDto);
		order.setUser(user.get());
		order.setIdOrder(id);
		order.setUpdatedAt(Instant.now());
		
		// Update order
		order = orderRepository.save(order);
		
		Set<Long> productsToDelete = orderDetailRepository.findByOrderId(id)
				.stream()
				.map(od -> od.getKey().getProduct().getIdProduct())
				.collect(Collectors.toSet());
		Set<Long> productsIds = new HashSet<Long>();
		
		// Logic to update order details
		if(!CollectionUtils.isEmpty(orderDto.getOrderDetails())) {
			
			List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
			Set<Product> productsToUpdate = new HashSet<Product>();
			
			for (OrderDetailDto dto : orderDto.getOrderDetails()) {
				OrderDetail orderDetail = dtoToOrderDetailConverter.convert(dto);
				Optional<Product> productO = productRepository.findById(dto.getIdProduct());
				
				// Validate if the product is eligible to order
				Product product = validateProductForOrder(productO, dto, productsIds);
				productsToUpdate.add(product);
				
				// Assign key to order detail after passing all the validations
				OrderDetailKey key = new OrderDetailKey(order, product);
				orderDetail.setKey(key);
				
				orderDetails.add(orderDetail);
				
				productsToDelete.remove(product.getIdProduct());
			}
			
			// Insert details for the order
			orderDetails = orderDetailRepository.saveAll(orderDetails);
			order.setOrderDetails(orderDetails);
			
			// Update stock of ordered products
			productRepository.saveAll(productsToUpdate);
			
			// Delete records for discarded products
			for(Long p : productsToDelete) {
				Product prod = new Product();
				prod.setIdProduct(p);
				orderDetailRepository.deleteById(new OrderDetailKey(order, prod));
			}
			
			order.setOrderDetails(orderDetails);
		}
		
		return orderToDtoConverter.convert(order);

	}
	
	private Product validateProductForOrder(Optional<Product> productO, OrderDetailDto dto, Set<Long> productsIds) {
		// Product exists validation
		if (!productO.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					MessageFormat.format(ErrorMessage.PRODUCT_NOT_FOUND, dto.getIdProduct()));

		Product product = productO.get();

		// Stock validation
		if(product.getStock() < dto.getQuantity())
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					MessageFormat.format(ErrorMessage.PRODUCT_NOT_ENOUGH_STOCK_TO_ORDER, dto.getIdProduct()));

		// Calculate new stock value after operation
		product.setStock(product.getStock() - dto.getQuantity());

		// Not duplicated product in order validation
		if(!productsIds.add(product.getIdProduct()))
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					MessageFormat.format(ErrorMessage.DUPLICATE_PRODUCT_FOR_ORDER, dto.getIdProduct()));
		
		
		return product;
		
	}

}
