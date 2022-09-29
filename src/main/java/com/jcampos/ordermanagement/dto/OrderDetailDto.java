package com.jcampos.ordermanagement.dto;

import javax.validation.constraints.NotNull;

import com.jcampos.ordermanagement.constant.ErrorMessage;

import lombok.Data;

@Data
public class OrderDetailDto {
	
	private Long idOrder;
	
	@NotNull(message = "idProduct" + ErrorMessage.NOT_NULL_VALIDATION)
	private Long idProduct;
	
	private String name;
	
	private Integer stock;
	
	@NotNull(message = "quantity" + ErrorMessage.NOT_NULL_VALIDATION)
	private Integer quantity;
	
	@NotNull(message = "price" + ErrorMessage.NOT_NULL_VALIDATION)
	private Double price;
	
	
	private Double subtotal;

}
