package com.jcampos.ordermanagement.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.jcampos.ordermanagement.domain.Order;
import com.jcampos.ordermanagement.dto.OrderDto;

@Component
public class DtoToOrderConverter implements Converter<OrderDto, Order>{

	@Override
	public Order convert(OrderDto source) {
		if(source == null)
			return null;
		
		Order target = new Order();
		target.setReceiverName(source.getReceiverName());
		target.setTotal(source.getTotal());
		target.setStreetAddress(source.getStreetAddress());
		target.setCity(source.getCity());
		target.setState(source.getState());
		target.setCountry(source.getCountry());
		target.setZipCode(source.getZipCode());
		
		return target;
	}

}
