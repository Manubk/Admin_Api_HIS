package com.admin.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PlanDto {
	
	private Integer planId;
	
	private String  planName;
	
	private LocalDate planStartDate;
	
	private LocalDate planEndDate;
	
	private Integer planCategoryId;
	
	private String activeSw;
	
	private double benefitAmt;
}
