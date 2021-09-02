package com.viniyone.fsnproject.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.viniyone.fsnproject.domain.Order;

public abstract class AbstractEmailService implements EmailService{

	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Order obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromOrder(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromOrder(Order obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCustomer().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Order confirmed! Order id: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
	
}
