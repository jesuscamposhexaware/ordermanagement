package com.jcampos.ordermanagement.service.intf;

import java.util.List;

import com.jcampos.ordermanagement.dto.OrderDetailDto;

public interface IOrderDetailService {

	public List<OrderDetailDto> getOrderDetailsByOrderId(Long idOrder);
	
}
