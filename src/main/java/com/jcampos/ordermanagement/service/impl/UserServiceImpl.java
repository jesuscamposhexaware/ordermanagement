package com.jcampos.ordermanagement.service.impl;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.jcampos.ordermanagement.constant.ErrorMessage;
import com.jcampos.ordermanagement.converter.UserToDtoConverter;
import com.jcampos.ordermanagement.domain.User;
import com.jcampos.ordermanagement.dto.UserDto;
import com.jcampos.ordermanagement.repository.UserRepository;
import com.jcampos.ordermanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

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
	
	public UserDto getUserByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		
		if(!user.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					MessageFormat.format(ErrorMessage.USER_BY_EMAIL_NOT_FOUND, email));
		
		return userToDtoConverter.convert(user.get());
	}

}
