package com.ams.presentacion.user.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import com.ams.presentacion.user.messages.ExceptionMessages;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	HttpStatus status;

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	public UserNotFoundException(long id) {
		super(ExceptionMessages.USER_NOT_FOUND_EXCEPTION +id);
		this.status = HttpStatus.BAD_REQUEST;
	}
}
