package com.viniyone.fsnproject.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.viniyone.fsnproject.domain.TicketPayment;

@Service
public class TicketService {
	
	public void fillTicketPayment(TicketPayment paym, Date orderInstant) { 
		Calendar cal = Calendar.getInstance();
		cal.setTime(orderInstant);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		paym.setExpireDate(cal.getTime());
		
	}
	
}
