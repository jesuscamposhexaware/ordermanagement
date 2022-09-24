package com.jcampos.ordermanagement.dto;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class OrderDetailDto {
	
	private Integer orderId;
	
	private ProductDto product;
	
	private Integer quantity;
	
	private Double sold_price;
	
	private Double subtotal;

}
