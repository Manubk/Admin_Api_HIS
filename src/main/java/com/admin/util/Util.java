package com.admin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.admin.dto.MailRequirments;
import com.admin.entity.UserEntity;

import jakarta.mail.internet.MimeMessage;

@Component
public class Util {
	
	
	private static final Logger log = LoggerFactory.getLogger(Util.class);
	
	@Autowired
	private  JavaMailSender javaMailSender;
	
	public  boolean sendMail(MailRequirments mailRequirments) {
		log.info("sendMail");
		
		try {
			if(mailRequirments.getFile() != null) {
				MimeMessage mail = javaMailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(mail);
				helper.setFrom(mailRequirments.getFrom());
				helper.setTo(mailRequirments.getTo());
				helper.setSubject(mailRequirments.getSubject());
				helper.setText(mailRequirments.getBody());
				helper.addAttachment(mailRequirments.getFile().getName(), mailRequirments.getFile());
				
				javaMailSender.send(mail);
				
				return true;
			}else {
				SimpleMailMessage mail = new SimpleMailMessage();
				mail.setFrom(mailRequirments.getFrom());
				mail.setTo(mailRequirments.getTo());
				mail.setSubject(mailRequirments.getSubject());
				mail.setText(mailRequirments.getBody());
				javaMailSender.send(mail);
				
				return true;
			}
		} catch (Exception e) {
			log.error("sendMail");
			e.printStackTrace();
			return false;
		}

	}

	public  String generatePass(UserEntity user) {
		log.info("generatePass");
		
		String fullName = user.getFullName();
		String email = user.getEmail();
		String ssn = user.getSsn();
		
		String pass = fullName.length()+""+ssn.substring(2,5);
		
		return pass;
	}
}
