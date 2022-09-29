package com.jcampos.ordermanagement.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import com.jcampos.ordermanagement.constant.ErrorMessage;
import com.jcampos.ordermanagement.converter.DtoToOrderConverter;
import com.jcampos.ordermanagement.converter.DtoToOrderDetailConverter;
import com.jcampos.ordermanagement.converter.OrderToDtoConverter;
import com.jcampos.ordermanagement.domain.Order;
import com.jcampos.ordermanagement.domain.OrderDetail;
import com.jcampos.ordermanagement.domain.Product;
import com.jcampos.ordermanagement.domain.User;
import com.jcampos.ordermanagement.dto.OrderDetailDto;
import com.jcampos.ordermanagement.dto.OrderDto;
import com.jcampos.ordermanagement.repository.OrderDetailRepository;
import com.jcampos.ordermanagement.repository.OrderRepository;
import com.jcampos.ordermanagement.repository.ProductRepository;
import com.jcampos.ordermanagement.repository.UserRepository;
import com.jcampos.ordermanagement.service.impl.OrderServiceImpl;


public class OrderServiceTests {

	private static OrderServiceImpl orderServiceImpl;

	private static OrderRepository orderRepository;  

	private static OrderDetailRepository orderDetailRepository;

	private static UserRepository userRepository;

	private static ProductRepository productRepository;

	private static OrderToDtoConverter orderToDtoConverter;

	private static DtoToOrderConverter dtoToOrderConverter;

	private static DtoToOrderDetailConverter dtoToOrderDetailConverter;

	@BeforeAll
	public static void init(){
		orderRepository = mock(OrderRepository.class); 
		orderToDtoConverter = mock(OrderToDtoConverter.class);
		dtoToOrderConverter = mock(DtoToOrderConverter.class);
		userRepository = mock(UserRepository.class);
		dtoToOrderDetailConverter = mock(DtoToOrderDetailConverter.class);
		productRepository = mock(ProductRepository.class);
		orderDetailRepository = mock(OrderDetailRepository.class);
		orderServiceImpl = new OrderServiceImpl(orderRepository, orderDetailRepository, userRepository, productRepository, orderToDtoConverter, dtoToOrderConverter, dtoToOrderDetailConverter);
	}

	@Test
	public void getOrderById_ok() {

		Order order = new  Order();
		Optional<Order> o = Optional.of(order);
		Long orderId = Mockito.anyLong();

		when(orderRepository.findById(orderId)).thenReturn(o);
		when(orderToDtoConverter.convert(o.get())).thenReturn(new OrderDto());

		assertNotNull(orderServiceImpl.getOrderById(orderId));
	}

	@Test
	public void getOrderById_NotFound() {

		Optional<Order> t = Optional.empty();
		Long orderId = Mockito.anyLong();


		when(orderRepository.findById(orderId)).thenReturn(t);

		ResponseStatusException responseStatusException =  assertThrows(ResponseStatusException.class, () -> {
			orderServiceImpl.getOrderById(orderId);
		});

		String expectedMessage = MessageFormat.format(ErrorMessage.ORDER_NOT_FOUND, orderId) ;
		String actualMessage = responseStatusException.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void getOrdersByUserId_Empty() {

		Long userId = Mockito.anyLong();
		List<Order> orders = new ArrayList<>();

		when(orderRepository.findByUserIdUser(userId)).thenReturn(orders);

		assertNotNull(orderServiceImpl.getOrdersByUserId(userId));    
	}

	@Test
	public void getOrdersByUserId_NotEmpty() {

		Optional<Order> t = Optional.empty();
		Long userId = Mockito.anyLong();
		List<Order> orders = new ArrayList<Order>() {{add(new Order());}};

		when(orderRepository.findByUserIdUser(userId)).thenReturn(orders);

		assertTrue(orderServiceImpl.getOrdersByUserId(userId).size()>0);    
	}

	@Test
	public void createOrder_ok() {
		Order order = new  Order();
		OrderDto orderDto = getNewOrder();
		Product product = getNewProduct() ;
		Optional<Product> productOpt = Optional.of(product);
		Optional<User> user = Optional.of(getNewUser());
		List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
		
		when(userRepository.findById(orderDto.getIdUser())).thenReturn(user);
		when(dtoToOrderConverter.convert(orderDto)).thenReturn(order);
		when(productRepository.findById(orderDto.getOrderDetails().get(0).getIdProduct())).thenReturn(productOpt);
		when(dtoToOrderDetailConverter.convert(orderDto.getOrderDetails().get(0))).thenReturn(getOrderDetail());
		when(orderDetailRepository.saveAll(orderDetails)).thenReturn(orderDetails);
		when(orderRepository.save(order)).thenReturn(order);
		orderServiceImpl.createOrder(orderDto);
	}


	@Test
	public void createOrder_userNotFound() {
		OrderDto orderDto = getNewOrder();
		Optional<User> user = Optional.empty();

		when(userRepository.findById(orderDto.getIdUser())).thenReturn(user);

		ResponseStatusException responseStatusException =  assertThrows(ResponseStatusException.class, () -> {
			orderServiceImpl.createOrder(orderDto);
		});

		String expectedMessage = MessageFormat.format(ErrorMessage.USER_NOT_FOUND, orderDto.getIdUser()) ;
		String actualMessage = responseStatusException.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void createOrder_productNotEnoughStockToOrder() {
		Order order = new  Order();
		OrderDto orderDto = getNewOrder();
		Product product = getNewProduct() ;
		product.setStock(0);
		Optional<Product> productOpt = Optional.of(product);
		Optional<User> user = Optional.of(getNewUser());
		when(userRepository.findById(orderDto.getIdUser())).thenReturn(user);
		when(dtoToOrderConverter.convert(orderDto)).thenReturn(order);
		when(productRepository.findById(orderDto.getOrderDetails().get(0).getIdProduct())).thenReturn(productOpt);
		when(dtoToOrderDetailConverter.convert(orderDto.getOrderDetails().get(0))).thenReturn(getOrderDetail());


		ResponseStatusException responseStatusException =  assertThrows(ResponseStatusException.class, () -> {
			orderServiceImpl.createOrder(orderDto);
		});

		String expectedMessage = MessageFormat.format(ErrorMessage.PRODUCT_NOT_ENOUGH_STOCK_TO_ORDER, orderDto.getOrderDetails().get(0).getIdProduct()) ;
		String actualMessage = responseStatusException.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void createOrder_productNotFound() {
		Order order = new  Order();
		OrderDto orderDto = getNewOrder();
		//Product product = getNewProduct() ;
		Optional<Product> productOpt = Optional.empty();
		Optional<User> user = Optional.of(getNewUser());
		when(userRepository.findById(orderDto.getIdUser())).thenReturn(user);
		when(dtoToOrderConverter.convert(orderDto)).thenReturn(order);
		when(productRepository.findById(orderDto.getOrderDetails().get(0).getIdProduct())).thenReturn(productOpt);

		ResponseStatusException responseStatusException =  assertThrows(ResponseStatusException.class, () -> {
			orderServiceImpl.createOrder(orderDto);
		});

		String expectedMessage = MessageFormat.format(ErrorMessage.PRODUCT_NOT_FOUND, orderDto.getOrderDetails().get(0).getIdProduct()) ;
		String actualMessage = responseStatusException.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}



	public void createOrder_duplicateProductForOrder() {
		Order order = new  Order();
		OrderDto orderDto = getNewOrderWithDuplicateProducts();
		Product product = getNewProduct() ;
		Optional<Product> productOpt = Optional.of(product);
		Optional<User> user = Optional.of(getNewUser());
		when(userRepository.findById(orderDto.getIdUser())).thenReturn(user);
		when(dtoToOrderConverter.convert(orderDto)).thenReturn(order);
		when(productRepository.findById(orderDto.getOrderDetails().get(0).getIdProduct())).thenReturn(productOpt);
		when(dtoToOrderDetailConverter.convert(orderDto.getOrderDetails().get(0))).thenReturn(getOrderDetail());


		ResponseStatusException responseStatusException =  assertThrows(ResponseStatusException.class, () -> {
			orderServiceImpl.createOrder(orderDto);
		});

		String expectedMessage = MessageFormat.format(ErrorMessage.DUPLICATE_PRODUCT_FOR_ORDER, orderDto.getOrderDetails().get(0).getIdProduct()) ;
		String actualMessage = responseStatusException.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}


	@Test
	public void deleteOrder_ok() {

		Order order = new  Order();
		Optional<Order> o = Optional.of(order);
		Long orderId = Mockito.anyLong();

		when(orderRepository.findById(orderId)).thenReturn(o);

		orderServiceImpl.deleteOrder(orderId);
	}


	@Test
	public void deleteOrder_NotFound() {

		Optional<Order> o = Optional.empty();
		Long orderId = Mockito.anyLong();


		when(orderRepository.findById(orderId)).thenReturn(o);

		ResponseStatusException responseStatusException =  assertThrows(ResponseStatusException.class, () -> {
			orderServiceImpl.deleteOrder(orderId);
		});

		String expectedMessage = MessageFormat.format(ErrorMessage.ORDER_NOT_FOUND, orderId) ;
		String actualMessage = responseStatusException.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void updateOrder_ok() {
		Order order = new  Order();
		OrderDto orderDto = new OrderDto();
		Long userId = Mockito.anyLong();
		orderDto.setIdUser(userId);
		User user = new User();
		Optional<User> userOpt = Optional.of(user);
		when(orderRepository.existsById(userId)).thenReturn(true);
		when(userRepository.findById(userId)).thenReturn(userOpt);
		
		when(dtoToOrderConverter.convert(orderDto)).thenReturn(order);

		orderServiceImpl.updateOrder(userId,orderDto);
	}

	@Test
	public void updateOrder_NotFound() {

		OrderDto orderDto = getNewOrder();
		when(orderRepository.existsById(Mockito.anyLong())).thenReturn(false);
		
		ResponseStatusException responseStatusException =  assertThrows(ResponseStatusException.class, () -> {
			orderServiceImpl.updateOrder(0l,orderDto);
		});

		String expectedMessage = MessageFormat.format(ErrorMessage.ORDER_NOT_FOUND, 0l) ;
		String actualMessage = responseStatusException.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void updateOrderDetails_ok() {
		Order order = new  Order();
		OrderDto orderDto = getNewOrder();

		when(orderRepository.existsById(Mockito.anyLong())).thenReturn(true);
		when(dtoToOrderConverter.convert(orderDto)).thenReturn(order);
		when(orderRepository.save(order)).thenReturn(order);
		orderServiceImpl.updateOrder(1l,orderDto);
	}
	
	private Product getNewProduct() {
		Product product = new Product();
		product.setIdProduct(10L);
		product.setStock(10);
		return product;
	}	

	private OrderDto getNewOrder() {
		OrderDto order = new OrderDto();
		List<OrderDetailDto> list = new ArrayList() {{add(getOrderDetailDto());}};
		order.setIdUser(100l);
		order.setOrderDetails(list);
		return order;
	}

	private OrderDto getNewOrderWithDuplicateProducts() {
		OrderDto order = new OrderDto();
		List<OrderDetailDto> list = new ArrayList() {{add(getOrderDetailDto());add(getOrderDetailDto());}};
		order.setIdUser(Mockito.anyLong());
		order.setOrderDetails(list);
		return order;
	}

	private User getNewUser() {
		User user = new User();
		return user;
	}

	private OrderDetail getOrderDetail() {
		OrderDetail orderDetail = new OrderDetail();
		return orderDetail;
	}

	private OrderDetailDto getOrderDetailDto() {
		OrderDetailDto orderDetailDto = new OrderDetailDto();
		orderDetailDto.setQuantity(1);
		return orderDetailDto;
	}

}
