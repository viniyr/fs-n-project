package com.viniyone.fsnproject.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.viniyone.fsnproject.domain.Order;

public interface EmailService {

	void sendOrderConfirmationEmail(Order obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Order obj); 
	
	void sendHtmlEmail(MimeMessage msg); 
	
}
