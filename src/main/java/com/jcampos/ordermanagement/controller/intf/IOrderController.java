package com.jcampos.ordermanagement.controller.intf;

import java.util.List;

import com.jcampos.ordermanagement.dto.OrderDetailDto;
import com.jcampos.ordermanagement.dto.OrderDto;

public interface IOrderController {
	
	public List<OrderDto> getByUserId(Integer idUser);
	
	public List<OrderDetailDto> getDetailsByOrderId(Integer id);
	
	public OrderDto create(OrderDto orderDto);
	
	public OrderDto delete(Integer id);
	
	public OrderDto update(Integer id, OrderDto orderDto);

}
