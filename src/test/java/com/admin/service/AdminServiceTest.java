package com.admin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.admin.dto.PlanCategoryDto;
import com.admin.dto.PlanDto;
import com.admin.entity.PlanCategoryEntity;
import com.admin.entity.PlanEntity;
import com.admin.repo.PlanCategoryRepo;
import com.admin.repo.PlanRepo;
import com.admin.repo.UserEntityRepo;
import com.admin.serviceInterface.IAdminService;
import com.admin.util.Util;
import java.util.Optional;

@SpringBootTest
public class AdminServiceTest {
	
	@Mock
	private PlanRepo planRepo;
	
	@Mock
	private PlanCategoryRepo planCategoryRepo;
	
	@Mock
	private UserEntityRepo userRepo;
	
	@MockBean
	private Util util;
	
	@InjectMocks
	private AdminService service;
	

	

//	@DisplayName("Saving the Categoryes and it should return list of categoryes")
//	public void createUpdateCat(PlanCategoryDto planCategoryDto) {
//	
//		PlanCategoryDto mockCategoryDto = new PlanCategoryDto();
//		mockCategoryDto.setActiveSw("A");
//		mockCategoryDto.setCategoryName("Family");
//		
//		
//		
//		List<PlanCategoryDto>  mockCategoryDtoList = Mockito.mock(ArrayList.class);
//		mockCategoryDtoList.add(new PlanCategoryDto(1,"Senior Citezon","A"));
//		mockCategoryDtoList.add(new PlanCategoryDto(1,"Children","A"));
//		mockCategoryDtoList.add(mockCategoryDto);
//		
//		
//		
//	
//		Mockito.when(planCategoryRepo.save(mockCategoryDto)).thenReturn(mockCategoryDto);
//		Mockito.when(service.findAllCategory()).thenReturn(mockCategoryDtoList);
//		
//		List<PlanCategoryDto> actualValue = service.createUpdateCat(mockCategoryDto);
//		
//		assertEquals(mockCategoryDtoList, actualValue);
//	
//	}
	
	@Test
	@Disabled
	public void testCategoryStatusChange() {
		
		AdminService spy = Mockito.spy(service);
		
		List<PlanCategoryDto>  mockCategoryDtoList = new ArrayList<>();
		mockCategoryDtoList.add(new PlanCategoryDto(1,"Senior Citezon","A"));
		mockCategoryDtoList.add(new PlanCategoryDto(1,"Children","A"));
		
		PlanCategoryDto mockPlanCategoryDto = new PlanCategoryDto(1,"Family","A");
		
		PlanCategoryEntity mockCategoryEntity = new PlanCategoryEntity(1,"Family","A");
		Optional<PlanCategoryEntity> optionalCat = Optional.of(mockCategoryEntity);
		
		Mockito.when(planCategoryRepo.findById(mockPlanCategoryDto.getCategoryId())).thenReturn(optionalCat);
		Mockito.doReturn(mockCategoryDtoList).when(spy).findAllCategory();
		
		List<PlanCategoryDto> actualValue = service.categoryStatusChange(mockPlanCategoryDto);
		
		assertEquals(mockCategoryDtoList, actualValue);
	}
	
	@Test
	@DisplayName("getting all the values from database")
	public void testGetAllCategoryes() {

		List<PlanCategoryDto>  mockCategoryDtoList = new ArrayList<>();
		mockCategoryDtoList.add(new PlanCategoryDto(1,"Senior Citezon","A"));
		mockCategoryDtoList.add(new PlanCategoryDto(1,"Children","A"));
		
		List<PlanCategoryEntity> mockCategoryEntitys = new ArrayList<>();
		mockCategoryEntitys.add(new PlanCategoryEntity(1,"Senior Citezon","A"))	;
		mockCategoryEntitys.add(new PlanCategoryEntity(1,"Children","A"));
		
		
		Mockito.when(planCategoryRepo.findAll()).thenReturn(mockCategoryEntitys);
		
		
		List<PlanCategoryDto> actualValues = service.findAllCategory();
		
		assertEquals(mockCategoryDtoList, actualValues);
	}
	
	@Test
	@DisplayName("Creating Plan")
	public void testCreateOrUpdatePlan() {
		List<PlanDto> plans = new ArrayList<>();
		plans.add(new PlanDto(1, "SNAP", LocalDate.now(), LocalDate.now().plusMonths(6),1, "A", 95));
		plans.add(new PlanDto(2, "CCAP", LocalDate.now(), LocalDate.now().plusMonths(6),2, "A", 634));
		PlanDto mockPlanDto = new PlanDto(2, "MEDICADE", LocalDate.now(), LocalDate.now().plusMonths(6),3, "A", 634);
		
		AdminService spy = Mockito.spy(service);
		
		PlanEntity mockPlanEntity = new PlanEntity(1, "", 95, LocalDate.now(), LocalDate.now().plusMonths(6), 1, "A");
		Mockito.when(planRepo.save(mockPlanEntity)).thenReturn(mockPlanEntity);
		
		Mockito.doReturn(plans).when(spy).findAllPlans();

		List<PlanDto> actualValue = service.createOrUpdatePlan(mockPlanDto);
		
		assertEquals(plans, actualValue);
	}
}
