package com.jcampos.ordermanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jcampos.ordermanagement.dto.OrderDetailDto;
import com.jcampos.ordermanagement.dto.OrderDto;
import com.jcampos.ordermanagement.service.intf.IOrderService;

@Service
public class OrderService implements IOrderService {

	@Override
	public List<OrderDto> getOrdersByUserId(Integer idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDetailDto> getOrderDetailsByOrderId(Integer idOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDto createOrder(OrderDto orderDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDto deleteOrder(Integer idOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDto updateOrder() {
		// TODO Auto-generated method stub
		return null;
	}

}
