package com.viniyone.fsnproject.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.viniyone.fsnproject.domain.enums.PaymentStatus;

@Entity
public class TicketPayment extends Payment{
	private static final long serialVersionUID = 1L;
	
	private Date expireDate;
	private Date paymentDate;
	
	public TicketPayment() {
	}

	public TicketPayment(Integer id, PaymentStatus status, Order order, Date expireDate, Date paymentDate) {
		super(id, status, order);
		this.expireDate = expireDate;
		this.paymentDate = paymentDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	
	
	
}
