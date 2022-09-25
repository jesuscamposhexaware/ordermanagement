package com.jcampos.ordermanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.jcampos.ordermanagement.converter.UserToDtoConverter;
import com.jcampos.ordermanagement.dto.UserDto;
import com.jcampos.ordermanagement.repository.UserRepository;
import com.jcampos.ordermanagement.service.intf.IUserService;

public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserToDtoConverter userToDtoConverter;
	
	@Override
	public List<UserDto> getAllUsers() {
		
		List<UserDto> userList = userRepository.findAll().stream()
				.map(u -> userToDtoConverter.convert(u))
				.collect(Collectors.toList());
		
		return userList;
	}

}
