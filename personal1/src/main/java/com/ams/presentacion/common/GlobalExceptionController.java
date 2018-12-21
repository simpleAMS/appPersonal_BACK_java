package com.ams.presentacion.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import com.ams.presentacion.common.exception.IdNotRequiredException;
import com.ams.presentacion.user.exception.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionController {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	<T> ExceptionResponse<T> userNotFoundHandler(UserNotFoundException ex) {
		return new ExceptionResponse(HttpStatus.NOT_FOUND, ex.getMessage(), this.getClass());
	}

	// Handling
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(ResponseStatusException.class)
	public ExceptionResponse handleException(ResponseStatusException ex) {
		return new ExceptionResponse(ex.getStatus(), ex.getMessage(), this.getClass());
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(IdNotRequiredException.class)
	public ExceptionResponse idNotNeeded(IdNotRequiredException ex) {
		return new ExceptionResponse(ex.getStatus(), ex.getMessage(), this.getClass());
	}
}
