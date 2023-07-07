package com.admin.serviceInterface;

import java.util.List;
import java.util.Map;

import com.admin.dto.MailRequirments;
import com.admin.dto.PlanCategoryDto;
import com.admin.dto.PlanDto;
import com.admin.dto.UserDto;
import com.admin.entity.UserEntity;

public interface IAdminService {
	
	public boolean isCateoryPresent(String categoryName);
	
	public Map<Integer, String> createUpdateCat(PlanCategoryDto planCategoryDto);
	
	public Map<Integer,String> categoryStatusChange(PlanCategoryDto planCategoryDto);
	
	public Map<Integer, String> findAllCategory();
	
	public boolean isPlanPresent(String planName);
	
	public List<PlanDto> createOrUpdatePlan(PlanDto plan);

	public List<PlanDto> deletePlan(Integer planId);

	public List<PlanDto> planStatusChange(PlanDto planDto);

	public List<PlanDto> findAllPlans();

	public boolean isUserEmailPresent(String Email);
	
	public boolean createUser(UserDto user);

	public UserDto userStatusChange(Long accId);

	public boolean updateUser(UserDto user);
	
	public boolean deleteUser(Long userId);

	public List<UserDto> findAllUsers();
	
	public MailRequirments createMail(UserEntity user);

}
