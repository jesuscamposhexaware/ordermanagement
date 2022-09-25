package com.jcampos.ordermanagement.converter;

import org.springframework.core.convert.converter.Converter;

import com.jcampos.ordermanagement.domain.Order;
import com.jcampos.ordermanagement.dto.OrderDto;

public class OrderToDtoConverter implements Converter<Order, OrderDto> {

	@Override
	public OrderDto convert(Order source) {
		// TODO Auto-generated method stub
		return null;
	}

}
