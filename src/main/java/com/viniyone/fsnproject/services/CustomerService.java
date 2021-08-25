package com.viniyone.fsnproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.viniyone.fsnproject.domain.Customer;
import com.viniyone.fsnproject.dto.CustomerDTO;
import com.viniyone.fsnproject.repositories.CustomerRepository;
import com.viniyone.fsnproject.services.exceptions.DataIntegrityException;
import com.viniyone.fsnproject.services.exceptions.ObjectNotFoundException;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repo;

	public Customer find(Integer id) { 
		  Optional<Customer> obj = repo.findById(id); 
		  return obj.orElseThrow(() -> new ObjectNotFoundException( 
		      "Object not found! Id: " + id + ", Type: " + Customer.class.getName())); 
		} 

	public Customer update(Customer obj) { 
		Customer newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) { 
		find(id);
		try {
		repo.deleteById(id);
		} 
		catch (DataIntegrityViolationException e) { 
			throw new DataIntegrityException("It's not possible to delete because there is entities associated");
		}
	}
	
	public List<Customer> findAll() { 
		return repo.findAll();
	}
	
	public Page<Customer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) { 
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Customer fromDTO(CustomerDTO objDto) { 
		return new Customer(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
	}
	
	private void updateData(Customer newObj, Customer obj) { 
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
	
	
}
