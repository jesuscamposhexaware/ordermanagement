package com.jcampos.ordermanagement.exception;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.TypeMismatchException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jcampos.ordermanagement.constant.ErrorMessage;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(DataAccessException.class)
	private ResponseEntity<Object> handleSqlException(RuntimeException ex, WebRequest request) {
		ApiError apiError = new ApiError();
		List<String> errors = new ArrayList<String>();
		errors.add(ErrorMessage.PERSISTENCE_ERROR);
		apiError.setError(errors);
		return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(WebExchangeBindException.class)
    private ResponseEntity<ApiError> handleWebExchangeBindException(WebExchangeBindException ex, WebRequest request) {
		ApiError apiError = new ApiError();
		List<String> errors = new ArrayList<String>();
		errors.add(ex.getMessage());
		apiError.setError(errors);
		return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(TypeMismatchException.class)
    private ResponseEntity<ApiError> handleTypeMismatchException(TypeMismatchException ex, WebRequest request) {
		ApiError apiError = new ApiError();
		List<String> errors = new ArrayList<String>();
		errors.add(ex.getMessage());
		apiError.setError(errors);
		return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(ResponseStatusException.class)
    private ResponseEntity<Object> handleException(ResponseStatusException ex, WebRequest request) {
		ApiError apiError = new ApiError();
		List<String> errors = new ArrayList<String>();
		errors.add(ex.getMessage());
		apiError.setError(errors);
		return new ResponseEntity<>(apiError, ex.getStatus());
    }
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError();
		List<String> errors = new ArrayList<String>();
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
		      errors.add(error.getDefaultMessage());
		}
		apiError.setError(errors);
		return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
	}
	
}
