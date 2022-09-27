package com.jcampos.ordermanagement.service;

import java.util.List;

import com.jcampos.ordermanagement.dto.UserDto;

public interface UserService {

	public List<UserDto> getAllUsers();
	public UserDto getUserById(Long id);
	public UserDto getUserByEmail(String email);
}
