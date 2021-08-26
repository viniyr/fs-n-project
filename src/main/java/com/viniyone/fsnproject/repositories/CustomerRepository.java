package com.viniyone.fsnproject.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viniyone.fsnproject.domain.Customer;

//Data access (CRUD)
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	@Transactional(readOnly =true)
	Customer findByEmail(String email);
	
}
