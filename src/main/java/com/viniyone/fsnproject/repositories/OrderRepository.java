package com.viniyone.fsnproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viniyone.fsnproject.domain.Order;

//Data access (CRUD)
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

	
	
}
