package com.tweetapp.exception;

public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 5754964805455963481L;

	public UserAlreadyExistsException(String msg) {
		super(msg);
	}
}
