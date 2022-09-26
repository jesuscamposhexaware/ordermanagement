package com.jcampos.ordermanagement.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.jcampos.ordermanagement.domain.User;
import com.jcampos.ordermanagement.dto.UserDto;

@Component
public class UserToDtoConverter implements Converter<User, UserDto> {

	@Override
	public UserDto convert(User source) {
		if(source == null)
			return null;
		
		UserDto target = new UserDto();
		target.setUserId(source.getIdUser());
		target.setEmail(source.getEmail());
		target.setName(source.getName());
		target.setLastName(source.getLastName());
		target.setAge(source.getAge());
		return null;
	}

}