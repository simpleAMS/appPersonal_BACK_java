package com.ams.presentacion.common;

import org.springframework.http.HttpStatus;

public class RequestResponse {
	private int status;
	private String message;
	private Object result;
	private HttpStatus httpStatus;

	public RequestResponse(int status, String message, Object result) {
		this.status = status;
		this.message = message;
		this.result = result;
	}
	
	public RequestResponse(HttpStatus status, String message, Object result) {
		this.httpStatus = status;
		this.message = message;
		this.result = result;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
