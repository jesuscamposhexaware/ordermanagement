package com.jcampos.ordermanagement.service.intf;

import java.util.List;

import com.jcampos.ordermanagement.dto.UserDto;

public interface IUserService {

	public List<UserDto> getAllUsers();
}
