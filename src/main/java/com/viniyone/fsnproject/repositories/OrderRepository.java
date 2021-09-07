package com.viniyone.fsnproject.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viniyone.fsnproject.domain.Customer;
import com.viniyone.fsnproject.domain.Order;

//Data access (CRUD)
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

	@Transactional(readOnly=true)
	Page<Order> findByCustomer(Customer customer, Pageable pageRequest);
	
}
