package com.jcampos.ordermanagement.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.jcampos.ordermanagement.constant.ErrorMessage;

import lombok.Data;

@Data
public class OrderDto {
	
	private Long idOrder;
	
	@NotNull(message = "idUser" + ErrorMessage.NOT_NULL_VALIDATION)
	private Long idUser;
	
	@NotNull(message = "receiverName" + ErrorMessage.NOT_NULL_VALIDATION)
	private String receiverName;
	
	private String giftMessage;
	
	private String giftMessageType;
	
	private String status;
	
	@NotNull(message = "total" + ErrorMessage.NOT_NULL_VALIDATION)
	private Double total;
	
	@NotNull(message = "streetAddress" + ErrorMessage.NOT_NULL_VALIDATION)
	private String streetAddress;
	
	@NotNull(message = "city" + ErrorMessage.NOT_NULL_VALIDATION)
	private String city;
	
	@NotNull(message = "state" + ErrorMessage.NOT_NULL_VALIDATION)
	private String state;
	
	@NotNull(message = "country" + ErrorMessage.NOT_NULL_VALIDATION)
	private String country;
	
	@NotNull(message = "zipCode" + ErrorMessage.NOT_NULL_VALIDATION)
	private String zipCode;
	
	private String createdAt;
	
	private String updatedAt;
	
	@Valid @NotNull(message = "orderDetails" + ErrorMessage.NOT_NULL_VALIDATION)
	private List<OrderDetailDto> orderDetails;
}
