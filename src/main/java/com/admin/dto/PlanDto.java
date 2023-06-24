package com.admin.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanDto {
	
	private Integer planId;
	
	private String  planName;
	
	private LocalDate planStartDate;
	
	private LocalDate planEndDate;
	
	private Integer planCategoryId;
	
	private String activeSw;
	
	private double benefitAmt;
}
