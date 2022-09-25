package com.jcampos.ordermanagement.service.intf;

import java.util.List;

import com.jcampos.ordermanagement.dto.OrderDto;

public interface IOrderService {

	public List<OrderDto> getOrdersByUserId(Long idUser);
	
	public OrderDto createOrder(OrderDto orderDto);
	
	public void deleteOrder(Long idOrder);
	
	public OrderDto updateOrder(Long id, OrderDto orderDto);
	
}
