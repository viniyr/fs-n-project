package com.viniyone.fsnproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viniyone.fsnproject.domain.City;
import com.viniyone.fsnproject.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository repo;
	
	public List<City> findByState(Integer stateId) { 
		return repo.findCities(stateId);
	}
	
}
