package com.jcampos.ordermanagement.converter;

import org.springframework.core.convert.converter.Converter;

import com.jcampos.ordermanagement.domain.OrderDetail;
import com.jcampos.ordermanagement.dto.OrderDetailDto;

public class OrderDetailToDtoConverter implements Converter<OrderDetail, OrderDetailDto>{

	@Override
	public OrderDetailDto convert(OrderDetail source) {
		// TODO Auto-generated method stub
		return null;
	}

}
