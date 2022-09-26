package com.jcampos.ordermanagement.dto;

import java.time.Instant;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderDto {
	
	private Long idOrder;
	
	@NotNull
	private Long idUser;
	
	@NotNull
	private String receiverName;
	
	private Object giftMessage;
	
	private String status;
	
	@NotNull
	private Double total;
	
	@NotNull
	private String streetAddress;
	
	@NotNull
	private String city;
	
	@NotNull
	private String state;
	
	@NotNull
	private String country;
	
	@NotNull
	private String zipCode;
	
	private Instant createdAt;
	
	private Instant updatedAt;
	
	@NotNull
	private List<OrderDetailDto> orderDetails;
}
