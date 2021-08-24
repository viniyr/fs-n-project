package com.viniyone.fsnproject.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.viniyone.fsnproject.domain.Category;
import com.viniyone.fsnproject.repositories.CategoryRepository;
import com.viniyone.fsnproject.services.exceptions.DataIntegrityException;
import com.viniyone.fsnproject.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;

	public Category find(Integer id) { 
		  Optional<Category> obj = repo.findById(id); 
		  return obj.orElseThrow(() -> new ObjectNotFoundException( 
		      "Object not found! Id: " + id + ", Type: " + Category.class.getName())); 
		} 
	
	public Category insert(Category obj) { 
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Category update(Category obj) { 
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) { 
		find(id);
		try {
		repo.deleteById(id);
		} 
		catch (DataIntegrityViolationException e) { 
			throw new DataIntegrityException("It's not possible to delete a category that contains products!");
		}
	}
	
}
