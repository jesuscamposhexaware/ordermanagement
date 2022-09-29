package com.jcampos.ordermanagement.dto;

import lombok.Data;

@Data
public class UserDto {
	
	private Long idUser;
	
	private String email;
	
	private String role;
	
	private String name;
	
	private String lastName;
	
	private Integer age;

}
