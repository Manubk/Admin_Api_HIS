package com.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.constants.AppConstants;
import com.admin.dto.MailRequirments;
import com.admin.dto.PlanCategoryDto;
import com.admin.dto.PlanDto;
import com.admin.dto.UserDto;
import com.admin.entity.PlanCategoryEntity;
import com.admin.entity.PlanEntity;
import com.admin.entity.UserEntity;
import com.admin.exception.DataPresentException;
import com.admin.exception.InvalidDetails;
import com.admin.exception.InvalidSsnException;
import com.admin.repo.PlanCategoryRepo;
import com.admin.repo.PlanRepo;
import com.admin.repo.UserEntityRepo;
import com.admin.serviceInterface.IAdminService;
import com.admin.util.Util;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class AdminService implements IAdminService {
	
	
	private static final Logger log = LoggerFactory.getLogger(AdminService.class);
	
	@Autowired
	private PlanRepo planRepo;
	
	@Autowired
	private PlanCategoryRepo planCategoryRepo;
	
	@Autowired
	private UserEntityRepo userRepo;
	
	@Autowired
	private Util util;
		
	@Override
	public Map<Integer, String> createUpdateCat(PlanCategoryDto planCategoryDto) {
		log.info("createUpdateCat ");
		
		if(planCategoryDto.getCategoryId() <= 0 ) {
			PlanCategoryEntity categoryName = planCategoryRepo.findByCategoryName(planCategoryDto.getCategoryName());
			if(categoryName != null) {
				throw new DataPresentException(AppConstants.EMAIL_PRESENT);
			}
		}
		
		PlanCategoryEntity category = new PlanCategoryEntity();
		
		BeanUtils.copyProperties(planCategoryDto, category);
		category.setActiveSw(AppConstants.ACTIVATE);
		
		log.info(planCategoryDto.toString());
		log.info(category.toString());
		
		PlanCategoryEntity savedCategory = planCategoryRepo.save(category);
		
		return findAllCategory();
	}

	@Override
	public Map<Integer, String> categoryStatusChange(PlanCategoryDto planCategoryDto) {
		log.info("deactivateCategory categoryId = "+planCategoryDto.getCategoryId());
		
		Optional<PlanCategoryEntity> OCategory = planCategoryRepo.findById(planCategoryDto.getCategoryId());
		
		if(OCategory.isPresent()) {
			PlanCategoryEntity category = OCategory.get();
			
			if(planCategoryDto.getActiveSw().equalsIgnoreCase(AppConstants.ACTIVATE))
				category.setActiveSw(AppConstants.ACTIVATE);
			else
				category.setActiveSw(AppConstants.DE_ACTIVATE);
			
			planCategoryRepo.save(category);
		}
		return findAllCategory();
	}

	@Override
	public Map<Integer, String> findAllCategory() {
		log.info("findAllCetagory");
		
		List<PlanCategoryEntity> categorys = planCategoryRepo.findAll();
		Map planCategoryMap = new  HashMap<Integer, String>();
		
		for(PlanCategoryEntity category : categorys) {
			planCategoryMap.put(category.getCategoryId(), category.getCategoryName());
			
		}
		return planCategoryMap;
	}
	
	@Override
	public List<PlanDto> createOrUpdatePlan(PlanDto planDto) {
		log.info("createOrUpdatePlan planId = "+planDto.getPlanId());
		
		PlanEntity planEntity = planRepo.findByPlanName(planDto.getPlanName());
		
		if(planEntity != null)
			throw new DataPresentException("This Plan Already Present");
		
		PlanEntity plan = new PlanEntity();
		
		BeanUtils.copyProperties(planDto, plan);
		plan.setActiveSw(AppConstants.ACTIVATE);
		
		log.info(planDto.toString());
		log.info(plan.toString());
		
		PlanEntity save = planRepo.save(plan);
		
		return findAllPlans();
		
	}

	@Override
	public List<PlanDto> deletePlan(Integer planId) {
		log.info("deletePlan planId = "+planId);
		
		try {
			planRepo.deleteById(planId);
		} catch (Exception e) {
			log.error("deletePlan planId = "+planId);
			e.printStackTrace();
		}
		
		return findAllPlans() ;
	}

	@Override
	public List<PlanDto> planStatusChange(PlanDto planDto) {
		log.info("deactivatePlan planId = "+planDto.getPlanId());
		
		Optional<PlanEntity> OPlan = planRepo.findById(planDto.getPlanId());
		
		if(OPlan.isPresent()) {
			PlanEntity plan = OPlan.get();
			
			if(planDto.getActiveSw().equalsIgnoreCase(AppConstants.ACTIVATE))
				plan.setActiveSw(AppConstants.ACTIVATE);
			else
				plan.setActiveSw(AppConstants.DE_ACTIVATE);
			
			PlanEntity savedPlan = planRepo.save(plan);
		}
		return findAllPlans();
	}

	@Override
	public List<PlanDto> findAllPlans() {
		log.info("findAllPlans");
		
		List<PlanEntity> plans = planRepo.findAll();
		List<PlanDto> planDtos = new ArrayList<>();
		
		for(PlanEntity plan : plans) {
			PlanDto planDto = new PlanDto();
			
			BeanUtils.copyProperties(plan, planDto);
			planDtos.add(planDto);
		}
		return planDtos;
	}

	
	@Override
	public boolean createUser(UserDto userDto) throws InvalidSsnException {
		log.info("createUser");
	
			validateSsn(userDto.getSsn());
			
			if(userRepo.findByEmail(userDto.getEmail()) != null)
				throw new InvalidDetails(AppConstants.EMAIL_PRESENT);
				
			UserEntity user = new UserEntity();
			BeanUtils.copyProperties(userDto, user);
			user.setActiveSw(AppConstants.DE_ACTIVATE);
			
			log.info(userDto.toString());
			log.info(user.toString());
			
			user.setPass(util.generatePass(user));
			
			UserEntity savedUser = userRepo.save(user);
			
			// After Saving the user details  should send mail to user to change his/her pass
			
//			boolean isMailsend = util.sendMail(createMail(savedUser));
			
			if(savedUser.getAccId() != null)
				return true;

			return false;
	}

	@Override
	public UserDto userStatusChange(Long userId) {
		log.info("deactivateUser userId = "+userId);
		
		Optional<UserEntity> OUser = userRepo.findById(userId);
		
		if(OUser.isPresent()) {
			UserEntity user = OUser.get();
			if(user.getActiveSw().equalsIgnoreCase(AppConstants.ACTIVATE))
				user.setActiveSw(AppConstants.DE_ACTIVATE);
			else
				user.setActiveSw(AppConstants.ACTIVATE);

			UserEntity savedUser = userRepo.save(user);
			
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(savedUser, userDto);
			return  userDto;
		}
		return null;
	}

	@Override
	public boolean updateUser(UserDto userDto) {
		log.info("updateUser userId = "+userDto.getAccId());
		
		//TODO 
		
		return false;
	}

	@Override
	public List<UserDto> findAllUsers() {
		log.info("findAllusers");
		
		List<UserEntity> users = userRepo.findAll();
		List<UserDto> userDtos = new ArrayList<>();
		
		for(UserEntity user : users) {
			UserDto userDto = new UserDto();
			
			BeanUtils.copyProperties(user, userDto);
			userDtos.add(userDto);
		}
		
		return userDtos;
	}

	@Override
	public MailRequirments createMail(UserEntity user) {
		log.info("createMail userId = "+user.getAccId());
		
		MailRequirments mailReq = new MailRequirments();
		
	    String subject = AppConstants.RESET_PASS;
	    
	    String body = AppConstants.RESET_PASS_BODY;
	    
	    body = body.replaceAll("USERNAME", user.getFullName());
	    body = body.replaceAll("PASSWORD", user.getPass());
	    body = body.replaceAll("EMAIL", user.getEmail());
	    body = body.replaceAll("ACCID", user.getAccId().toString());	    
		
		mailReq.setFrom(AppConstants.FROM_MAIL);
		mailReq.setTo(user.getEmail());
		mailReq.setBody(body);
		mailReq.setSubject(subject);
		return mailReq;
	}
	
	
	private boolean validateSsn(String ssN)  {
		log.info("validateSsn");
		
		if(ssN == null || ssN.equals("") || ssN.length() != 9) {
			throw new  InvalidSsnException(AppConstants.INVALID_SSN);
		}
		return true;
	}

	@Override
	public boolean isCateoryPresent(String categoryName) {
		log.info("isCategotyPresent name = "+categoryName);
		PlanCategoryEntity category = planCategoryRepo.findByCategoryName(categoryName);
		
		if(category == null )
			return false;
		
		return true;
	}

	@Override
	public boolean isPlanPresent(String planName) {
		log.info("isPlanPresent");
		PlanEntity plan = planRepo.findByPlanName(planName);
		
		if(plan == null)
			return false;
		
		return true;
	}

	@Override
	public boolean isUserEmailPresent(String email) {
		log.info("isUserEmailPresent");
		
		UserEntity user = userRepo.findByEmail(email);
		
		if(user != null)
			return true;
		
		return false;
	}

	@Override
	public boolean deleteUser(Long UserId) {
		log.info("deleteUser userId = "+UserId);
		try {
			userRepo.deleteById(UserId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


}
