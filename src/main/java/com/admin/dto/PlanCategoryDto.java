package com.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanCategoryDto {
	
	private Integer categoryId;
	
	private String categoryName;
	
	private String activeSw;
	
}
