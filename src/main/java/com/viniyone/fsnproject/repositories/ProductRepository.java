package com.viniyone.fsnproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viniyone.fsnproject.domain.Product;

//Data access (CRUD)
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	
	
}
