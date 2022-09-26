package com.jcampos.ordermanagement.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.jcampos.ordermanagement.domain.Order;
import com.jcampos.ordermanagement.dto.OrderDto;

@Component
public class OrderToDtoConverter implements Converter<Order, OrderDto> {

	@Override
	public OrderDto convert(Order source) {
		if(source == null)
			return null;
		
		OrderDto target = new OrderDto();
		target.setIdOrder(source.getIdOrder());
		target.setIdUser(source.getUser().getIdUser());
		target.setGiftMessage(source.getGiftMessage());
		target.setStatus(source.getStatus());
		target.setTotal(source.getTotal());
		target.setStreetAddress(source.getStreetAddress());
		target.setCity(source.getCity());
		target.setState(source.getState());
		target.setCountry(source.getCountry());
		target.setZipCode(source.getZipCode());
		target.setCreatedAt(source.getCreatedAt());
		target.setUpdatedAt(source.getUpdatedAt());
		
		return target;
	}

}
