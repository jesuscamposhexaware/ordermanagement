package com.jcampos.ordermanagement.converter;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.jcampos.ordermanagement.domain.Order;
import com.jcampos.ordermanagement.dto.OrderDto;

@Component
public class OrderToDtoConverter implements Converter<Order, OrderDto> {

	@Autowired
	private OrderDetailToDtoConverter orderDetailToDtoConverter;
	
	@Override
	public OrderDto convert(Order source) {
		if(source == null)
			return null;
		
		OrderDto target = new OrderDto();
		target.setIdOrder(source.getIdOrder());
		target.setIdUser(source.getUser().getIdUser());
		target.setReceiverName(source.getReceiverName());
		target.setGiftMessage(source.getGiftMessage());
		target.setGiftMessageType(source.getGiftMessageType());
		target.setStatus(source.getStatus());
		target.setTotal(source.getTotal());
		target.setStreetAddress(source.getStreetAddress());
		target.setCity(source.getCity());
		target.setState(source.getState());
		target.setCountry(source.getCountry());
		target.setZipCode(source.getZipCode());
		target.setCreatedAt(source.getCreatedAt() != null ? source.getCreatedAt().toString() : null);
		target.setUpdatedAt(source.getUpdatedAt() != null ? source.getUpdatedAt().toString() : null);
		
		target.setOrderDetails(source.getOrderDetails() != null ? source.getOrderDetails()
				.stream()
				.map(o ->orderDetailToDtoConverter.convert(o))
				.collect(Collectors.toList()) : new ArrayList<>());
		
		return target;
	}

}
