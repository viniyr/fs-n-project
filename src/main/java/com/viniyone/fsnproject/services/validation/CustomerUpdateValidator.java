package com.viniyone.fsnproject.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.viniyone.fsnproject.domain.Customer;
import com.viniyone.fsnproject.dto.CustomerDTO;
import com.viniyone.fsnproject.repositories.CustomerRepository;
import com.viniyone.fsnproject.resources.exceptions.FieldMessage; 
 
public class CustomerUpdateValidator implements ConstraintValidator<CustomerUpdate, CustomerDTO> { 
 
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private CustomerRepository repo;
	
    @Override 
    public void initialize(CustomerUpdate ann) { 
    } 
   
    @Override 
    public boolean isValid(CustomerDTO objDto, ConstraintValidatorContext context) { 
 
    	@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
    	Integer uriId = Integer.parseInt(map.get("id"));
    	
        List<FieldMessage> list = new ArrayList<>(); 
        
        Customer aux = repo.findByEmail(objDto.getEmail());
        if (aux != null && !aux.getId().equals(uriId)) {
        	list.add(new FieldMessage("email", "Email already exists"));
        }
        
        for (FieldMessage e : list) { 
            context.disableDefaultConstraintViolation(); 
            context.buildConstraintViolationWithTemplate(e.getMessage()) 
            .addPropertyNode(e.getFieldName()).addConstraintViolation(); 
        } 
        return list.isEmpty(); 
    } 
}