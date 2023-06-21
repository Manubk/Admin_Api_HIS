package com.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.admin.dto.PlanCategoryDto;
import com.admin.serviceInterface.IAdminService;

@RestController
public class CategoryController {
	
	
	private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private IAdminService adminServie;
	
	@PostMapping("/category")
	public ResponseEntity<List<PlanCategoryDto>> createCategory(@RequestBody PlanCategoryDto categoryDto){
		log.info("createCategory cat = "+categoryDto.getCategoryName());
		
		List<PlanCategoryDto> categoryDtos = adminServie.createUpdateCat(categoryDto);
		
		return new ResponseEntity<List<PlanCategoryDto>>(categoryDtos,HttpStatus.OK);
		
	}
	
	@PostMapping("/category/sw")
	public ResponseEntity<List<PlanCategoryDto>> changeStatusOfCategory(@RequestBody PlanCategoryDto categoryDto){
		log.info("changeStatusOfCategory cat ="+categoryDto.getCategoryName()+" SW = "+categoryDto.getActiveSw());
		
		List<PlanCategoryDto> categoryDtos = adminServie.categoryStatusChange(categoryDto);
		
		return new ResponseEntity<List<PlanCategoryDto>>(categoryDtos,HttpStatus.OK);
		
	}
	
	@GetMapping("/categorys")
	public ResponseEntity<List<PlanCategoryDto>> findAllCategory(){
		log.info("findAllCategory");
		
		List<PlanCategoryDto> categoryDtos = adminServie.findAllCategory();
		
		return new ResponseEntity<List<PlanCategoryDto>>(categoryDtos,HttpStatus.OK);
	}
	
	
	
}
