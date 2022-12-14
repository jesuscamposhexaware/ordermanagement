package com.jcampos.ordermanagement.controller;

import java.util.List;

import com.jcampos.ordermanagement.dto.UserDto;

public interface UserController {

	public List<UserDto> getAll();
	public UserDto getById(Long id);
	public UserDto getByEmail(String email);
	
}
