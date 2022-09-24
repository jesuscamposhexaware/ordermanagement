package com.jcampos.ordermanagement.service.intf;

import java.util.List;

import com.jcampos.ordermanagement.dto.OrderDetailDto;
import com.jcampos.ordermanagement.dto.OrderDto;

public interface IOrderService {

	public List<OrderDto> getOrdersByUserId(Integer idUser);
	
	public List<OrderDetailDto> getOrderDetailsByOrderId(Integer idOrder);
	
	public OrderDto createOrder(OrderDto orderDto);
	
	public OrderDto deleteOrder(Integer idOrder);
	
	public OrderDto updateOrder();
	
}
