package com.jcampos.ordermanagement.service.impl;

import java.text.MessageFormat;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jcampos.ordermanagement.constant.Constants;
import com.jcampos.ordermanagement.constant.ErrorMessage;
import com.jcampos.ordermanagement.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<com.jcampos.ordermanagement.domain.User> user = userRepository.findByEmail(username);
		
		if(!user.isPresent())
			throw new UsernameNotFoundException(MessageFormat
					.format(ErrorMessage.USER_BY_EMAIL_NOT_FOUND, username));
		
		User userDetails = new User(user.get().getEmail(), user.get().getPassword(),
				buildAuthorities(user.get().getRole()));
		
		return userDetails;
	}
	
	private Set<SimpleGrantedAuthority> buildAuthorities(String roles) {
		Set<SimpleGrantedAuthority> authorities = new LinkedHashSet<>();
		
		for(String role : roles.split(Constants.ROLES_SEPARATOR))
			authorities.add(new SimpleGrantedAuthority(role));
		
		return authorities;
	}

}
