package com.java.exception;

public class MyCustomException extends RuntimeException {

	private static final long serialVersionUID = -655203852970332508L;

	public MyCustomException() {
		super();
	}

	public MyCustomException(String message) {
		super(message);
	}

}
