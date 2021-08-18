package com.viniyone.fsnproject.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viniyone.fsnproject.domain.Order;
import com.viniyone.fsnproject.repositories.OrderRepository;
import com.viniyone.fsnproject.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;

	public Order find(Integer id) { 
		  Optional<Order> obj = repo.findById(id); 
		  return obj.orElseThrow(() -> new ObjectNotFoundException( 
		      "Object not found! Id: " + id + ", Type: " + Order.class.getName())); 
		} 
}
