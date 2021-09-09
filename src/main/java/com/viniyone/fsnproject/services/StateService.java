package com.viniyone.fsnproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viniyone.fsnproject.domain.State;
import com.viniyone.fsnproject.repositories.StateRepository;

@Service
public class StateService {

	@Autowired
	private StateRepository repo;
	
	public List<State> findAll() {
		return repo.findAllByOrderByName();
	}
	
}
