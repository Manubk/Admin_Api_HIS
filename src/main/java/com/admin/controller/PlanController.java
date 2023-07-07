package com.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.admin.dto.PlanCategoryDto;
import com.admin.dto.PlanDto;
import com.admin.serviceInterface.IAdminService;

@CrossOrigin
@RestController
public class PlanController {
	
	
	private static final Logger log = LoggerFactory.getLogger(PlanController.class);

	@Autowired
	private IAdminService adminService;
	
	@PostMapping("/plan")
	public ResponseEntity<List<PlanDto>> createPlan(@RequestBody PlanDto planDto){
		log.info("createPlan ");
		
		List<PlanDto> planDtos = adminService.createOrUpdatePlan(planDto);
		
		return new ResponseEntity<List<PlanDto>>(planDtos,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<List<PlanDto>> deletePlan(@PathVariable Integer planId){
		log.info("deleteplan planId = "+planId);
		
		List<PlanDto> planDtos = adminService.deletePlan(planId);
		
		return new ResponseEntity<List<PlanDto>>(planDtos,HttpStatus.OK);
	}
	
	@PutMapping("/plan/sw")
	public ResponseEntity<List<PlanDto>> changePlanStatus(@RequestBody PlanDto planDto){
		log.info("changePlanStatus planId = "+planDto.getPlanId());
		
		List<PlanDto> planDtos = adminService.planStatusChange(planDto);
		
		return new ResponseEntity<List<PlanDto>>(planDtos,HttpStatus.OK);
	}
	
	@GetMapping("/plans")
	public ResponseEntity<List<PlanDto>> findALlPlans(){
		log.info("findAllPlans");
		
		List<PlanDto> planDtos = adminService.findAllPlans();
		
		return new ResponseEntity<List<PlanDto>>(planDtos,HttpStatus.OK);
	}

}
