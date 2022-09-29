package com.jcampos.ordermanagement.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderDetailDto {
	
	private Long idOrder;
	
	@NotNull
	private Long idProduct;
	
	private String name;
	
	private Integer stock;
	
	@NotNull
	private Integer quantity;
	
	@NotNull
	private Double price;
	
	
	private Double subtotal;

}
