package com.admin.entity;

import com.admin.constants.AppConstants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "PLAN_CATEGORY")
public class PlanCategoryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CATEGORY_ID")
	private Integer categoryId;
	
	@Column(name = "CATEGORY_NAME")
	private String categoryName;
	
	@Column(name = "ACTIVE_SW", columnDefinition = "varchar(1) default 'A'")
	private String activeSw = AppConstants.ACTIVATE;
}
