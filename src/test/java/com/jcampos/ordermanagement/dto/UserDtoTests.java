package com.jcampos.ordermanagement.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UserDtoTests {

	@Test
    public void testGetsAddressDtoParameters() throws Exception {
		UserDto userDto = new UserDto();
		userDto.getAge();
		userDto.getEmail();
		userDto.getIdUser();
		userDto.getLastName();
		userDto.getName();
		userDto.getRole();
		assertNotNull(userDto);
    }
	
	@Test
    public void testSetsAddressDtoParameters() throws Exception {
		UserDto userDto = new UserDto();
		userDto.setAge(1);
		userDto.setEmail("");
		userDto.setIdUser(1l);
		userDto.setLastName("");
		userDto.setName("");
		userDto.setRole("");
		assertNotNull(userDto);
    }
	
	@Test
    public void testAddressDtoParameters() throws Exception {
		UserDto userDto = new UserDto();
		
		UserDto userDto2 = new UserDto();
		
		assertNotNull(userDto.toString());
		assertNotNull(userDto.hashCode());
		assertTrue(userDto.equals(userDto2));
    }
}