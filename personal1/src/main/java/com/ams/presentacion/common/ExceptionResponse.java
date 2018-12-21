package com.ams.presentacion.common;

import org.springframework.http.HttpStatus;

public class ExceptionResponse<T> {

	private HttpStatus httpStatus;
	private String message;
	private Class<T> clas;

	public ExceptionResponse(HttpStatus httpStatus, String message, Class<T> clas) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
		this.clas = clas;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Class<T> getClas() {
		return clas;
	}

	public void setClas(Class<T> clas) {
		this.clas = clas;
	}

}
