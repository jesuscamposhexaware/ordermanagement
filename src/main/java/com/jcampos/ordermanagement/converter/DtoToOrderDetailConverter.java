package com.jcampos.ordermanagement.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.jcampos.ordermanagement.domain.OrderDetail;
import com.jcampos.ordermanagement.dto.OrderDetailDto;

@Component
public class DtoToOrderDetailConverter implements Converter<OrderDetailDto, OrderDetail>{

	@Override
	public OrderDetail convert(OrderDetailDto source) {
		if(source == null)
			return null;
		
		OrderDetail target = new OrderDetail();
		target.setQuantity(source.getQuantity());
		target.setSoldPrice(source.getSold_price());
		target.setSubtotal(source.getSubtotal());
		
		return target;
	}

}
