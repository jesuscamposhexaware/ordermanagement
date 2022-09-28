package com.jcampos.ordermanagement.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductDto {
	
	@NotNull
	private Long idProduct;
	
	private String name;
	
	private String description;
	
	private Double price;
	
	private Integer stock;
	
	private String picture;

}
