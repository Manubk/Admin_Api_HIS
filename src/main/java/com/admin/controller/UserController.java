package com.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.admin.constants.AppConstants;
import com.admin.dto.UserDto;
import com.admin.serviceInterface.IAdminService;

import io.swagger.v3.oas.annotations.Hidden;

@RestController
public class UserController {
	
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private JavaMailSender mailSendr;
	
	@PostMapping("/user")
	public ResponseEntity<String> createUser(@RequestBody UserDto userDto){
		log.info("createUser");
		
		boolean isCreated = adminService.createUser(userDto);
		
		return (isCreated)? new ResponseEntity<String>(AppConstants.SUCCESSFUL,HttpStatus.ACCEPTED):
			new ResponseEntity<String>(AppConstants.FAILED,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@PutMapping("/user/sw/{accId}")
	public ResponseEntity<UserDto> changestatus(Long accId){
		log.info("changeStatus accId = "+accId);
		
		UserDto userDto = adminService.userStatusChange(accId);
		
		return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
	}
	
	@PutMapping("/user")
	public ResponseEntity<String> updateUser(UserDto userDto){
		log.info("updateUser accId = "+userDto.getAccId());
		
		boolean isUpdated = adminService.updateUser(userDto);
		
		return (isUpdated)?new ResponseEntity<String>(AppConstants.SUCCESSFUL,HttpStatus.OK):
			new ResponseEntity<String>(AppConstants.FAILED,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("users")
	public ResponseEntity<List<UserDto>> findAllUser(){
		log.info("findAllUser");
		
		List<UserDto> userDtos = adminService.findAllUsers();
		
		return new ResponseEntity<List<UserDto>>(userDtos,HttpStatus.OK);
	}
	
	@Hidden
	@GetMapping("/mail")
	public String mail(){
		SimpleMailMessage mail = new SimpleMailMessage();
		log.info("preparing mail");
		mail.setFrom(AppConstants.FROM_MAIL);
		mail.setTo("yoyomaltesh4040@gmail.com");
		mail.setSubject("testing");
		mail.setText(AppConstants.RESET_PASS_BODY);
		
		mailSendr.send(mail);
		return "sent";
	}
}
