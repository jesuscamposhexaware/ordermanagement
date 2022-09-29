package com.jcampos.ordermanagement.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.server.ResponseStatusException;

import com.jcampos.ordermanagement.constant.ErrorMessage;
import com.jcampos.ordermanagement.converter.UserToDtoConverter;
import com.jcampos.ordermanagement.domain.User;
import com.jcampos.ordermanagement.dto.ProductDto;
import com.jcampos.ordermanagement.dto.UserDto;
import com.jcampos.ordermanagement.repository.UserRepository;
import com.jcampos.ordermanagement.service.impl.UserServiceImpl;

public class UserServiceTests {

	private static UserRepository userRepository;
	
	private static UserToDtoConverter userToDtoConverter;
	
	private static UserServiceImpl userServiceImpl;
	
	@BeforeAll
	public static void init(){
		userRepository = mock(UserRepository.class); 
		userToDtoConverter = mock(UserToDtoConverter.class); 
		
		userServiceImpl = new UserServiceImpl(userRepository,userToDtoConverter);
	}

	
	@Test
	public void getAllUsers_ok() {
		UserDto userDto = new UserDto();
		when(userToDtoConverter.convert(null)).thenReturn(userDto);
		assertNotNull(userServiceImpl.getAllUsers());
	}
	
	@Test
	public void getUserById_ok() {
		User user = new User();
		Optional<User> userOpt = Optional.of(user);
		Long userId = Mockito.anyLong();
		UserDto userDto = new UserDto();
		when(userRepository.findById(userId)).thenReturn(userOpt);
		when(userToDtoConverter.convert(userOpt.get())).thenReturn(userDto);
		assertNotNull(userServiceImpl.getUserById(userId));
	}
	
	@Test
	public void getUserById_NotFound() {
		User user = new User();
		Optional<User> userOpt = Optional.empty();
		Long userId = Mockito.anyLong();
		
		when(userRepository.findById(userId)).thenReturn(userOpt);
		
		ResponseStatusException responseStatusException =  assertThrows(ResponseStatusException.class, () -> {
			userServiceImpl.getUserById(userId);
		});

		String expectedMessage = MessageFormat.format(ErrorMessage.USER_NOT_FOUND, userId) ;
		String actualMessage = responseStatusException.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void getUserByEmail_ok() {
		User user = new User();
		Optional<User> userOpt = Optional.of(user);
		String emailUser = Mockito.anyString();
		UserDto userDto = new UserDto();
		
		when(userRepository.findByEmail(emailUser)).thenReturn(userOpt);
		when(userToDtoConverter.convert(userOpt.get())).thenReturn(userDto);
		assertNotNull(userServiceImpl.getUserByEmail(emailUser));
	}
	
	@Test
	public void getUserByEmail_NotFound() {
		User user = new User();
		Optional<User> userOpt = Optional.empty();
		String emailUser = Mockito.anyString();
		
		when(userRepository.findByEmail(emailUser)).thenReturn(userOpt);
		
		ResponseStatusException responseStatusException =  assertThrows(ResponseStatusException.class, () -> {
			userServiceImpl.getUserByEmail(emailUser);
		});

		String expectedMessage = MessageFormat.format(ErrorMessage.USER_BY_EMAIL_NOT_FOUND, emailUser) ;
		String actualMessage = responseStatusException.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
}
