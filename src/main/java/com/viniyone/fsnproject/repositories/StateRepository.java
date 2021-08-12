package com.viniyone.fsnproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viniyone.fsnproject.domain.State;

//Data access (CRUD)
@Repository
public interface StateRepository extends JpaRepository<State, Integer>{

	
	
}
