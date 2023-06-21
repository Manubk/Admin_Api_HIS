package com.admin.serviceInterface;

import java.util.List;

import com.admin.dto.MailRequirments;
import com.admin.dto.PlanCategoryDto;
import com.admin.dto.PlanDto;
import com.admin.dto.UserDto;
import com.admin.entity.UserEntity;

public interface IAdminService {
	
	public List<PlanCategoryDto> createUpdateCat(PlanCategoryDto planCategoryDto);
	
	public List<PlanCategoryDto> categoryStatusChange(PlanCategoryDto planCategoryDto);
	
	public List<PlanCategoryDto> findAllCategory();
	
	public List<PlanDto> createOrUpdatePlan(PlanDto plan);

	public List<PlanDto> deletePlan(Integer planId);

	public List<PlanDto> planStatusChange(PlanDto planDto);

	public List<PlanDto> findAllPlans();

	public boolean createUser(UserDto user);

	public UserDto userStatusChange(Long accId);

	public boolean updateUser(UserDto user);

	public List<UserDto> findAllUsers();
	
	public MailRequirments createMail(UserEntity user);

}
