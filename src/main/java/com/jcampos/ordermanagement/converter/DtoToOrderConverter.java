package com.jcampos.ordermanagement.converter;

import org.springframework.core.convert.converter.Converter;

import com.jcampos.ordermanagement.domain.Order;
import com.jcampos.ordermanagement.dto.OrderDto;

public class DtoToOrderConverter implements Converter<OrderDto, Order>{

	@Override
	public Order convert(OrderDto source) {
		// TODO Auto-generated method stub
		return null;
	}

}
