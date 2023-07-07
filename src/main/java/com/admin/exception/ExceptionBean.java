package com.admin.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ExceptionBean {

	public String code;
	public String domine;
	public String msg;
	public LocalDateTime date;
	public String reason;
	
	
}
