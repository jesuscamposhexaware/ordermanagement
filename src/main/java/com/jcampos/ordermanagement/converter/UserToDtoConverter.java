package com.jcampos.ordermanagement.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.jcampos.ordermanagement.domain.User;
import com.jcampos.ordermanagement.dto.UserDto;

@Component
public class UserToDtoConverter implements Converter<User, UserDto> {

	@Override
	public UserDto convert(User source) {
		// TODO Auto-generated method stub
		return null;
	}

}
