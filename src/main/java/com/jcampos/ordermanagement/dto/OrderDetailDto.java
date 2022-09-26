package com.jcampos.ordermanagement.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderDetailDto {
	
	private Long orderId;
	
	@NotNull
	private Long productId;
	
	@NotNull
	private Integer quantity;
	
	@NotNull
	private Double sold_price;
	
	@NotNull
	private Double subtotal;

}
