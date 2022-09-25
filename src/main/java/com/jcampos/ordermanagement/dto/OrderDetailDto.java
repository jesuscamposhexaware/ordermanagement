package com.jcampos.ordermanagement.dto;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class OrderDetailDto {
	
	private Long orderId;
	
	private Long productId;
	
	private Integer quantity;
	
	private Double sold_price;
	
	private Double subtotal;

}
