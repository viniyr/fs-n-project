package com.viniyone.fsnproject.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.viniyone.fsnproject.domain.Address;
import com.viniyone.fsnproject.domain.City;
import com.viniyone.fsnproject.domain.Customer;
import com.viniyone.fsnproject.domain.enums.Profile;
import com.viniyone.fsnproject.domain.enums.TypeCustomer;
import com.viniyone.fsnproject.dto.CustomerDTO;
import com.viniyone.fsnproject.dto.NewCustomerDTO;
import com.viniyone.fsnproject.repositories.AddressRepository;
import com.viniyone.fsnproject.repositories.CustomerRepository;
import com.viniyone.fsnproject.security.UserSS;
import com.viniyone.fsnproject.services.exceptions.AuthorizationException;
import com.viniyone.fsnproject.services.exceptions.DataIntegrityException;
import com.viniyone.fsnproject.services.exceptions.ObjectNotFoundException;

@Service
public class CustomerService {

	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private CustomerRepository repo;

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private S3Service s3Service;
	
	public Customer find(Integer id) {

		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Access denied");
		}
		
		Optional<Customer> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Customer.class.getName()));
	}
	
	@Transactional
	public Customer insert(Customer obj) { 
		obj.setId(null);
		obj = repo.save(obj);
		addressRepository.saveAll(obj.getAddress());
		return obj;
		
		
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
			throw new DataIntegrityException("It's not possible to delete because there is orders associated");
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
		return new Customer(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null,null);
	}
	
	public Customer fromDTO(NewCustomerDTO objDto) {
		Customer cus = new Customer(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOrCnpj(), TypeCustomer.toEnum(objDto.getType()), pe.encode(objDto.getPassword()));
		City city = new City(objDto.getCityId(), null, null);
		Address add = new Address(null, objDto.getStreet(), objDto.getNumber(), objDto.getComplement(), objDto.getComplement(), objDto.getZip(), cus , city);
		cus.getAddress().add(add);
		cus.getPhones().add(objDto.getPhone1());
		if (objDto.getPhone2() != null ) { 
			cus.getPhones().add(objDto.getPhone2());
		}
		if (objDto.getPhone3() != null ) { 
			cus.getPhones().add(objDto.getPhone3());
		}
		return cus;
	}
	
	private void updateData(Customer newObj, Customer obj) { 
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
	
	public URI uploadProfilePicture(MultipartFile multiPartFile) { 
		return s3Service.uploadFile(multiPartFile);
	}
	
	
}
