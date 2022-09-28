package com.jcampos.ordermanagement.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@Override
	@GetMapping("/{id}")
	public UserDto getById(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	@Override
	@GetMapping("/email/{id}")
	public UserDto getByEmail(@PathVariable String id) {
		return userService.getUserByEmail(id);
	}

}
