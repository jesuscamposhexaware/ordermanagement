package com.jcampos.ordermanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BasicAuthSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf()
			.disable().authorizeRequests()
			.anyRequest().authenticated()
			.and().httpBasic();
		
		return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPassowordEncoder() {
		return new BCryptPasswordEncoder(); 
	}
}
