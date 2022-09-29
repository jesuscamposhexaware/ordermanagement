package com.jcampos.ordermanagement.exception;

import java.util.List;

import lombok.Data;

@Data
public class ApiError {
	
	private List<String> error;

}
