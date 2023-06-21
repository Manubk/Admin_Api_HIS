package com.admin.exception;

public class InvalidSsnException extends RuntimeException {
	
	public InvalidSsnException() {
	}
	
	public InvalidSsnException(String msg){
		super(msg);
	}
	
}
