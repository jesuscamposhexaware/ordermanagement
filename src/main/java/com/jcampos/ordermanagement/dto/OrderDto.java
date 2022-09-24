package com.jcampos.ordermanagement.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderDto {
	
	@NotNull
	private Long idUser;
	
	@NotNull
	private Long idAddress;
	
	@NotNull
	private String receiverName;
	
	@SuppressWarnings("unused")
	private Object giftMessage;
	
	@NotNull
	private List<ProductDto> products;
}
