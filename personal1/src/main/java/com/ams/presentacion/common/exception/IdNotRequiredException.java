package com.ams.presentacion.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.ams.presentacion.common.Messages;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IdNotRequiredException extends RuntimeException {

	HttpStatus status;

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public IdNotRequiredException() {
		super(Messages.ID_NOT_NEEDED );
		this.status = HttpStatus.BAD_REQUEST;
	}

}