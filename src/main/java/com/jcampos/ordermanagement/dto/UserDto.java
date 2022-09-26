package com.jcampos.ordermanagement.dto;

import lombok.Data;

@Data
public class UserDto {
	
	private Long userId;
	
	private String email;
	
	private String name;
	
	private String lastName;
	
	private Integer age;

}
