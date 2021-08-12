package com.viniyone.fsnproject;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.viniyone.fsnproject.domain.Address;
import com.viniyone.fsnproject.domain.Category;
import com.viniyone.fsnproject.domain.City;
import com.viniyone.fsnproject.domain.Customer;
import com.viniyone.fsnproject.domain.Product;
import com.viniyone.fsnproject.domain.State;
import com.viniyone.fsnproject.domain.enums.TypeCustomer;
import com.viniyone.fsnproject.repositories.AddressRepository;
import com.viniyone.fsnproject.repositories.CategoryRepository;
import com.viniyone.fsnproject.repositories.CityRepository;
import com.viniyone.fsnproject.repositories.CustomerRepository;
import com.viniyone.fsnproject.repositories.ProductRepository;
import com.viniyone.fsnproject.repositories.StateRepository;

@SpringBootApplication
public class FsNProjectApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(FsNProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");
		
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 280.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");
		
		City c1 = new City(null, "Uberlândia", st1);
		City c2 = new City(null, "São Paulo", st2);
		City c3 = new City(null, "Campinas", st2);
		
		st1.getCities().addAll(Arrays.asList(c1));
		st2.getCities().addAll(Arrays.asList(c2,c3));
		
		stateRepository.saveAll(Arrays.asList(st1,st2));
		cityRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Customer cus1 = new Customer(null, "Anya Yonoshi", "anyaya@gmail.com", "43910937705", TypeCustomer.NATURAL_PERSON);
		
		cus1.getPhones().addAll(Arrays.asList("11990262852", "27583604"));
		
		Address add1 = new Address(null, "Rua Herticular", "69" , "Casa com telhas", "Jordacity", "09298349", cus1, c2);
		Address add2 = new Address(null, "Rua Paselio", "96", "Portão azul", "Diadema", "09795321", cus1, c2);
		
		cus1.getAddress().addAll(Arrays.asList(add1, add2));
		
		customerRepository.saveAll(Arrays.asList(cus1));
		addressRepository.saveAll(Arrays.asList(add1,add2));		
	}

	
	
}
