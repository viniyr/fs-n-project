package com.viniyone.fsnproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viniyone.fsnproject.domain.City;

//Data access (CRUD)
@Repository
public interface CityRepository extends JpaRepository<City, Integer>{

	
	
}
