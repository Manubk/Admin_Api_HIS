package com.admin.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.admin.constants.AppConstants;

@RestControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = InvalidSsnException.class)
	public ResponseEntity<ExceptionBean> invalidSsnException(InvalidSsnException e){
		
		ExceptionBean exception = new ExceptionBean();
		
		exception.setCode(AppConstants.INVALID_SSN_CODE);
		exception.setMsg(e.getMessage());
		
		return new ResponseEntity<ExceptionBean>(exception,HttpStatus.BAD_REQUEST);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = InvalidDetails.class)
	public ResponseEntity<ExceptionBean> invalidDetails(InvalidDetails e){
		
		ExceptionBean exception  = new ExceptionBean();
		exception.setCode(AppConstants.INVALID_DETAILS_CODE);
		exception.setMsg(e.getMessage());
		
		return new ResponseEntity<ExceptionBean>(exception,HttpStatus.BAD_REQUEST);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = DataPresentException.class)
	public ResponseEntity<ExceptionBean> dataAlreadyPresent(DataPresentException e){
		
		ExceptionBean exception = new ExceptionBean();
		exception.setCode(AppConstants.DATA_EXISTS_CODE);
		exception.setDate(LocalDateTime.now());
		exception.setReason(AppConstants.DATA_EXISTS);
		exception.setMsg(e.getMessage());
		
		return new ResponseEntity<ExceptionBean> (exception,HttpStatus.BAD_REQUEST);
	}
}
