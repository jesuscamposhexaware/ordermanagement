package com.jcampos.ordermanagement.service;

import java.text.MessageFormat;
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
import com.jcampos.ordermanagement.service.intf.IOrderService;

@Service
public class OrderService implements IOrderService {
	
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
		
		return orderToDtoConverter.convert(order.get());
	}
	
	@Override
	public List<OrderDto> getOrdersByUserId(Long idUser) {
		return orderRepository.findByUserIdUser(idUser)
				.stream()
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
		
		// Save order and generate id order
		order = orderRepository.save(order);
		
		List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
		Set<Product> productsToUpdate = new HashSet<Product>();
		
		for (OrderDetailDto dto : orderDto.getOrderDetails()) {
			OrderDetail orderDetail = dtoToOrderDetailConverter.convert(dto);
			Optional<Product> productO = productRepository.findById(dto.getProductId());
			
			// Validate if the product is eligible to order
			Product product = validateProductForOrder(productO, dto, productsToUpdate);

			// Assign key to order detail after passing all the validations
			orderDetail.setKey(new OrderDetailKey(order, product));
			
			orderDetails.add(orderDetail);
		}
		
		// Insert details for the order
		orderDetailRepository.saveAll(orderDetails);
		
		// Update stock of ordered products
		productRepository.saveAll(productsToUpdate);
		
		return orderToDtoConverter.convert(order);
		
	}

	@Override
	public void deleteOrder(Long idOrder) {
		
		Optional<Order> order = orderRepository.findById(idOrder);
	
		if(order.isPresent())
			orderRepository.delete(order.get());
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
		
		Order order = dtoToOrderConverter.convert(orderDto);
		order.setIdOrder(id);
		
		// Update order
		order = orderRepository.save(order);
		
		// Logic to update order details
		if(!CollectionUtils.isEmpty(orderDto.getOrderDetails())) {
			
			Set<OrderDetailKey> orderDetailKeys = orderDetailRepository.findByKeyOrderIdOrder(id)
					.stream()
					.map(od -> od.getKey())
					.collect(Collectors.toSet());
			
			List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
			Set<Product> productsToUpdate = new HashSet<Product>();
			
			for (OrderDetailDto dto : orderDto.getOrderDetails()) {
				OrderDetail orderDetail = dtoToOrderDetailConverter.convert(dto);
				Optional<Product> productO = productRepository.findById(dto.getProductId());
				
				// Validate if the product is eligible to order
				Product product = validateProductForOrder(productO, dto, productsToUpdate);

				// Assign key to order detail after passing all the validations
				OrderDetailKey key = new OrderDetailKey(order, product);
				orderDetail.setKey(key);
				
				orderDetails.add(orderDetail);
				
				orderDetailKeys.remove(key);
			}
			
			// Insert details for the order
			orderDetailRepository.saveAll(orderDetails);
			
			// Delete records for discarded products
			orderDetailRepository.deleteAllById(orderDetailKeys);
		}
		
		return orderToDtoConverter.convert(order);

	}
	
	private Product validateProductForOrder(Optional<Product> productO, OrderDetailDto dto, Set<Product> productSet) {
		// Product exists validation
		if (!productO.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					MessageFormat.format(ErrorMessage.PRODUCT_NOT_FOUND, dto.getProductId()));

		Product product = productO.get();

		// Stock validation
		if(product.getStock() < dto.getQuantity())
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					MessageFormat.format(ErrorMessage.PRODUCT_NOT_ENOUGH_STOCK_TO_ORDER, dto.getProductId()));

		// Calculate new stock value after operation
		product.setStock(product.getStock() - dto.getQuantity());

		// Not duplicated product in order validation
		if(!productSet.add(product))
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					MessageFormat.format(ErrorMessage.DUPLICATE_PRODUCT_FOR_ORDER, dto.getProductId()));
		
		return product;
		
	}

}
