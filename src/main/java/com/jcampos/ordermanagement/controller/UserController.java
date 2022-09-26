package com.jcampos.ordermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcampos.ordermanagement.controller.intf.IUserController;
import com.jcampos.ordermanagement.dto.UserDto;
import com.jcampos.ordermanagement.service.intf.IUserService;

@RestController
@RequestMapping("/user")
public class UserController implements IUserController {
	
	@Autowired
	private IUserService userService;

	@Override
	@GetMapping
	public List<UserDto> getAll() {
		return userService.getAllUsers();
	}

}
