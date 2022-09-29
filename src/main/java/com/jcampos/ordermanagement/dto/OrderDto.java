package com.jcampos.ordermanagement.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class OrderDto {
	
	private Long idOrder;
	
	@NotNull
	private Long idUser;
	
	@NotNull
	private String receiverName;
	
	private byte[] giftMessage;
	
	private String giftMessageType;
	
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
	
	private String createdAt;
	
	private String updatedAt;
	
	@NotNull
	private List<OrderDetailDto> orderDetails;
}
