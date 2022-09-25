package com.jcampos.ordermanagement.converter;

import org.springframework.core.convert.converter.Converter;

import com.jcampos.ordermanagement.domain.OrderDetail;
import com.jcampos.ordermanagement.dto.OrderDetailDto;

public class DtoToOrderDetailConverter implements Converter<OrderDetailDto, OrderDetail>{

	@Override
	public OrderDetail convert(OrderDetailDto source) {
		// TODO Auto-generated method stub
		return null;
	}

}
