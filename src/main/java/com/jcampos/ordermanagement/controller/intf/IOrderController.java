package com.jcampos.ordermanagement.controller.intf;

import java.util.List;

import com.jcampos.ordermanagement.dto.OrderDto;

public interface IOrderController {
	
	public List<OrderDto> getByUserId(Long idUser);
	
	public OrderDto getById(Long id);
	
	public Object create(OrderDto orderDto);
	
	public Object delete(Long id);
	
	public OrderDto update(Long id, OrderDto orderDto);

}
