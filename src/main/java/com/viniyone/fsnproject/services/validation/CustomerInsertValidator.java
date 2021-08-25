package com.viniyone.fsnproject.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.viniyone.fsnproject.domain.enums.TypeCustomer;
import com.viniyone.fsnproject.dto.NewCustomerDTO;
import com.viniyone.fsnproject.resources.exceptions.FieldMessage;
import com.viniyone.fsnproject.services.validation.utils.BR; 
 
public class CustomerInsertValidator implements ConstraintValidator<CustomerInsert, NewCustomerDTO> { 
 
    @Override 
    public void initialize(CustomerInsert ann) { 
    } 
   
    @Override 
    public boolean isValid(NewCustomerDTO objDto, ConstraintValidatorContext context) { 
 
        List<FieldMessage> list = new ArrayList<>(); 
       
        if (objDto.getType().equals(TypeCustomer.NATURAL_PERSON.getCod()) && !BR.isValidCPF(objDto.getCpfOrCnpj())) {
        	list.add(new FieldMessage("cpfOrCnpj", "Invalid CPF"));
        }
        
        if (objDto.getType().equals(TypeCustomer.LEGAL_ENTITY.getCod()) && !BR.isValidCNPJ(objDto.getCpfOrCnpj())) {
        	list.add(new FieldMessage("cpfOrCnpj", "Invalid CNPJ"));
        }
        
        for (FieldMessage e : list) { 
            context.disableDefaultConstraintViolation(); 
            context.buildConstraintViolationWithTemplate(e.getMessage()) 
            .addPropertyNode(e.getFieldName()).addConstraintViolation(); 
        } 
        return list.isEmpty(); 
    } 
}