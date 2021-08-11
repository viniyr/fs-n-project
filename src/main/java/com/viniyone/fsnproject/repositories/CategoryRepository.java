package com.viniyone.fsnproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viniyone.fsnproject.domain.Category;

//Data access (CRUD)
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	
	
}
