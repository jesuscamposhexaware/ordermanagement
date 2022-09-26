package com.jcampos.ordermanagement.dto;

import lombok.Data;

@Data
public class AddressDto {

	private Long idAddress;
	
	private Long idUser;
	
	private String steetAddress;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private Integer zipCode;
	
}
