package com.viniyone.fsnproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viniyone.fsnproject.domain.Payment;

//Data access (CRUD)
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{

	
	
}
