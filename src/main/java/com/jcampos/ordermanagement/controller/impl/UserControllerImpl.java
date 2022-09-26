package com.jcampos.ordermanagement.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcampos.ordermanagement.controller.UserController;
import com.jcampos.ordermanagement.dto.UserDto;
import com.jcampos.ordermanagement.service.UserService;

@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {
	
	@Autowired
	private UserService userService;

	@Override
	@GetMapping
	public List<UserDto> getAll() {
		return userService.getAllUsers();
	}

}
