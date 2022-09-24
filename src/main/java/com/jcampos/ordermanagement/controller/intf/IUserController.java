package com.jcampos.ordermanagement.controller.intf;

import java.util.List;

import com.jcampos.ordermanagement.dto.UserDto;

public interface IUserController {

	public List<UserDto> getAll();
	
}
