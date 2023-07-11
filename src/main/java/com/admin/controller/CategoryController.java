package com.admin.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.admin.dto.PlanCategoryDto;
import com.admin.serviceInterface.IAdminService;

@CrossOrigin
@RestController
public class CategoryController {
	
	
	private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private IAdminService adminServie;
	
	@PostMapping("/category")
	public ResponseEntity<Map<Integer, String>> createCategory(@RequestBody PlanCategoryDto categoryDto){
		log.info("createCategory cat = "+categoryDto.getCategoryName());
		
		Map<Integer, String> allCategories = adminServie.createUpdateCat(categoryDto);
		
		return new ResponseEntity<Map<Integer, String>>(allCategories,HttpStatus.OK);
		
	}
	
	@PostMapping("/category/sw")
	public ResponseEntity<Map<Integer,String>> changeStatusOfCategory(@RequestBody PlanCategoryDto categoryDto){
		log.info("changeStatusOfCategory cat ="+categoryDto.getCategoryName()+" SW = "+categoryDto.getActiveSw());
		
		Map<Integer, String> allCategoryes = adminServie.categoryStatusChange(categoryDto);
		
		return new ResponseEntity<Map<Integer ,String>>(allCategoryes,HttpStatus.OK);
		
	}
	
	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> findAllCategory(){
		log.info("findAllCategory");
		
		 Map<Integer, String> allCategoryes = adminServie.findAllCategory();
		
		return new ResponseEntity<Map<Integer,String>>(allCategoryes,HttpStatus.OK);
	}
	
	
	
}
