package com.ams.presentacion.user.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import com.ams.presentacion.user.messages.ExceptionMessages;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class UserNotFounException extends RuntimeException {

	public UserNotFounException(long id) {
		super(ExceptionMessages.USER_NOT_FOUND_EXCEPTION +id);
	}
}
