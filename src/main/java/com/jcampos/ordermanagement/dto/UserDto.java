package com.jcampos.ordermanagement.dto;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class UserDto {
	
	private Long userId;
	
	private String email;
	
	private String name;
	
	private String lastName;
	
	private Integer age;

}
