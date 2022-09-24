package com.jcampos.ordermanagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcampos.ordermanagement.controller.intf.IUserController;
import com.jcampos.ordermanagement.dto.UserDto;

@RestController
@RequestMapping("/user")
public class UserController implements IUserController {

	@Override
	@GetMapping
	public List<UserDto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
