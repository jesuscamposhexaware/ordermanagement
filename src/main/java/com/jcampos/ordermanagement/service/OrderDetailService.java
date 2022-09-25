package com.jcampos.ordermanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.jcampos.ordermanagement.converter.OrderDetailToDtoConverter;
import com.jcampos.ordermanagement.dto.OrderDetailDto;
import com.jcampos.ordermanagement.repository.OrderDetailRepository;
import com.jcampos.ordermanagement.service.intf.IOrderDetailService;

public class OrderDetailService implements IOrderDetailService {
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private OrderDetailToDtoConverter orderDetailToDtoConverter;
	
	@Override
	public List<OrderDetailDto> getOrderDetailsByOrderId(Long idOrder) {
		
		return orderDetailRepository.findByOrderIdOrder(idOrder)
				.stream()
				.map(od -> orderDetailToDtoConverter.convert(od))
				.collect(Collectors.toList());
		
	}

}
