package com.java.exception;

public class MyCustomException extends Exception {

	private static final long serialVersionUID = -655203852970332508L;

	public MyCustomException() {
		super();
	}

	public MyCustomException(String message) {
		super(message);
	}

}
