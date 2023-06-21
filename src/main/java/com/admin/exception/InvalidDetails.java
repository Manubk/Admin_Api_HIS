package com.admin.exception;


public class InvalidDetails extends RuntimeException{
	
	public InvalidDetails() {
	}
	
	public InvalidDetails(String msg) {
		super(msg);
	}
}
