package com.viniyone.fsnproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viniyone.fsnproject.domain.OrderItem;

//Data access (CRUD)
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

	
	
}
