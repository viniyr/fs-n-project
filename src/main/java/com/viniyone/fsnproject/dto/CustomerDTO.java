package com.viniyone.fsnproject.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.viniyone.fsnproject.domain.Customer;

public class CustomerDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message="Field Required")
	@Length(min=5, max=120, message="Length needs to be between 5 and 80 characters")
	private String name;
	
	@NotEmpty(message="Field Required")
	@Email(message="Invalid email!")
	private String email;
	
	public CustomerDTO() {
	}

	public CustomerDTO(Customer obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
