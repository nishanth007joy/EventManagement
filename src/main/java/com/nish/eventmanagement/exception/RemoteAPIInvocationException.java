package com.nish.eventmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class RemoteAPIInvocationException extends RuntimeException {
	public RemoteAPIInvocationException() {
		super();
	}

	public RemoteAPIInvocationException(String message) {
		super(message);
	}

}
