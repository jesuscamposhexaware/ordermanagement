package com.jcampos.ordermanagement.service;

import java.util.List;

import com.jcampos.ordermanagement.dto.OrderDto;

public interface OrderService {

	public OrderDto getOrderById(Long id);
	
	public List<OrderDto> getOrdersByUserId(Long idUser);
	
	public OrderDto createOrder(OrderDto orderDto);
	
	public void deleteOrder(Long idOrder);
	
	public OrderDto updateOrder(Long id, OrderDto orderDto);
	
}
