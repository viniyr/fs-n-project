package com.viniyone.fsnproject.dto;

import java.io.Serializable;

import com.viniyone.fsnproject.domain.City;

public class CityDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private Integer id;
	
	public CityDTO() {
	}

	public CityDTO(City obj) {
		id = obj.getId();
 		name = obj.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
}
