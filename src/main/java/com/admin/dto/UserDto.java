package com.admin.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserDto {
	
	private Long accId;
	
	private String fullName;
	
	private String email;
	
	private String phoneNo;
	
	private String gender;
	
	private String ssn;
	
	private LocalDate dob;
	
	private String role;
	
	private String activeSw;
}
