package com.jcampos.ordermanagement.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.jcampos.ordermanagement.domain.OrderDetail;
import com.jcampos.ordermanagement.dto.OrderDetailDto;

@Component
public class OrderDetailToDtoConverter implements Converter<OrderDetail, OrderDetailDto>{

	@Override
	public OrderDetailDto convert(OrderDetail source) {
		if(source == null)
			return null;
		
		OrderDetailDto target = new OrderDetailDto();
		target.setIdOrder(source.getKey()
				.getOrder().getIdOrder());
		target.setIdProduct(source.getKey()
				.getProduct().getIdProduct());
		target.setQuantity(source.getQuantity());
		target.setPrice(source.getSoldPrice());
		target.setSubtotal(source.getSubtotal());
		target.setName(source.getKey().getProduct().getName());
		target.setStock(source.getKey().getProduct().getStock());
		
		return target;
	}

}
