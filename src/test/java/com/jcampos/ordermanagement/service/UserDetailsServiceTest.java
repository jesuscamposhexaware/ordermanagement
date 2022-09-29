package com.jcampos.ordermanagement.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.MessageFormat;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.server.ResponseStatusException;

import com.jcampos.ordermanagement.constant.ErrorMessage;
import com.jcampos.ordermanagement.domain.User;
import com.jcampos.ordermanagement.dto.ProductDto;
import com.jcampos.ordermanagement.repository.UserRepository;
import com.jcampos.ordermanagement.service.impl.UserDetailsServiceImpl;

public class UserDetailsServiceTest {

	
	private static UserRepository userRepository;
	
	private static UserDetailsServiceImpl userDetailsServiceImpl;
	
	@BeforeAll
	public static void init(){
		userRepository = mock(UserRepository.class); 
		
		
		
		userDetailsServiceImpl = new UserDetailsServiceImpl(userRepository);
	}
	
	
	@Test
	public void  loadUserByUsername_ok() {

		String username = Mockito.anyString();
		User user = new User();
		user.setRole("Admin");
		user.setEmail("jcampos@gmail.com");
		user.setPassword("Hexare");
		Optional<User> userOpt = Optional.of(user);
				
		when(userRepository.findByEmail(username)).thenReturn(userOpt);

		assertNotNull(userDetailsServiceImpl.loadUserByUsername(username));
	}
	
	@Test
	public void  loadUserByUsername_NotFoud() {

		String username = Mockito.anyString();
		User user = new User();
		user.setRole("Admin");
		user.setEmail("jcampos@gmail.com");
		user.setPassword("Hexare");
		Optional<User> userOpt = Optional.empty();
				
		when(userRepository.findByEmail(username)).thenReturn(userOpt);

		
		UsernameNotFoundException responseStatusException =  assertThrows(UsernameNotFoundException.class, () -> {
			userDetailsServiceImpl.loadUserByUsername(username);
		});

		String expectedMessage = MessageFormat.format(ErrorMessage.USER_BY_EMAIL_NOT_FOUND, username) ;
		String actualMessage = responseStatusException.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
		
	}
}
