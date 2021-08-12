package com.viniyone.fsnproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viniyone.fsnproject.domain.Customer;

//Data access (CRUD)
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	
	
}
