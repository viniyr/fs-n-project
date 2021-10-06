package com.viniyone.fsnproject.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.viniyone.fsnproject.domain.Address;
import com.viniyone.fsnproject.domain.CardPayment;
import com.viniyone.fsnproject.domain.Category;
import com.viniyone.fsnproject.domain.City;
import com.viniyone.fsnproject.domain.Customer;
import com.viniyone.fsnproject.domain.Order;
import com.viniyone.fsnproject.domain.OrderItem;
import com.viniyone.fsnproject.domain.Payment;
import com.viniyone.fsnproject.domain.Product;
import com.viniyone.fsnproject.domain.State;
import com.viniyone.fsnproject.domain.TicketPayment;
import com.viniyone.fsnproject.domain.enums.PaymentStatus;
import com.viniyone.fsnproject.domain.enums.Profile;
import com.viniyone.fsnproject.domain.enums.TypeCustomer;
import com.viniyone.fsnproject.repositories.AddressRepository;
import com.viniyone.fsnproject.repositories.CategoryRepository;
import com.viniyone.fsnproject.repositories.CityRepository;
import com.viniyone.fsnproject.repositories.CustomerRepository;
import com.viniyone.fsnproject.repositories.OrderItemRepository;
import com.viniyone.fsnproject.repositories.OrderRepository;
import com.viniyone.fsnproject.repositories.PaymentRepository;
import com.viniyone.fsnproject.repositories.ProductRepository;
import com.viniyone.fsnproject.repositories.StateRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;
	
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

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	public void instantiateTestDatabase() throws ParseException {

		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");
		Category cat3 = new Category(null, "Home, Bed & Bath");
		Category cat4 = new Category(null, "Electronics");
		Category cat5 = new Category(null, "Gardening");
		Category cat6 = new Category(null, "Decoration");
		Category cat7 = new Category(null, "Perfumery");

		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 280.00);
		Product p4 = new Product(null, "Office desk", 280.00);
		Product p5 = new Product(null, "Tower", 280.00);
		Product p6 = new Product(null, "Blanket", 280.00);
		Product p7 = new Product(null, "Tv true color", 280.00);
		Product p8 = new Product(null, "Hoe", 280.00);
		Product p9 = new Product(null, "Bedside lamp", 280.00);
		Product p10 = new Product(null, "Pending", 280.00);
		Product p11 = new Product(null, "Shampoo", 280.00);
		
		Product p12 = new Product(null, "Product 12", 10.00); 
		Product p13 = new Product(null, "Product 13", 10.00); 
		Product p14 = new Product(null, "Product 14", 10.00); 
		Product p15 = new Product(null, "Product 15", 10.00); 
		Product p16 = new Product(null, "Product 16", 10.00); 
		Product p17 = new Product(null, "Product 17", 10.00); 
		Product p18 = new Product(null, "Product 18", 10.00); 
		Product p19 = new Product(null, "Product 19", 10.00); 
		Product p20 = new Product(null, "Product 20", 10.00); 
		Product p21 = new Product(null, "Product 21", 10.00); 
		Product p22 = new Product(null, "Product 22", 10.00); 
		Product p23 = new Product(null, "Product 23", 10.00); 
		Product p24 = new Product(null, "Product 24", 10.00); 
		Product p25 = new Product(null, "Product 25", 10.00); 
		Product p26 = new Product(null, "Product 26", 10.00); 
		Product p27 = new Product(null, "Product 27", 10.00); 
		Product p28 = new Product(null, "Product 28", 10.00); 
		Product p29 = new Product(null, "Product 29", 10.00); 
		Product p30 = new Product(null, "Product 30", 10.00); 
		Product p31 = new Product(null, "Product 31", 10.00); 
		Product p32 = new Product(null, "Product 32", 10.00); 
		Product p33 = new Product(null, "Product 33", 10.00); 
		Product p34 = new Product(null, "Product 34", 10.00); 
		Product p35 = new Product(null, "Product 35", 10.00); 
		Product p36 = new Product(null, "Product 36", 10.00); 
		Product p37 = new Product(null, "Product 37", 10.00); 
		Product p38 = new Product(null, "Product 38", 10.00); 
		Product p39 = new Product(null, "Product 39", 10.00); 
		Product p40 = new Product(null, "Product 40", 10.00); 
		Product p41 = new Product(null, "Product 41", 10.00); 
		Product p42 = new Product(null, "Product 42", 10.00); 
		Product p43 = new Product(null, "Product 43", 10.00); 
		Product p44 = new Product(null, "Product 44", 10.00); 
		Product p45 = new Product(null, "Product 45", 10.00); 
		Product p46 = new Product(null, "Product 46", 10.00); 
		Product p47 = new Product(null, "Product 47", 10.00); 
		Product p48 = new Product(null, "Product 48", 10.00); 
		Product p49 = new Product(null, "Product 49", 10.00); 
		Product p50 = new Product(null, "Product 50", 10.00); 
		 
		cat1.getProducts().addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20, 
		p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38, 
		p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50)); 
		 
		p12.getCategories().add(cat1); 
		p13.getCategories().add(cat1); 
		p14.getCategories().add(cat1); 
		p15.getCategories().add(cat1); 
		p16.getCategories().add(cat1); 
		p17.getCategories().add(cat1); 
		p18.getCategories().add(cat1); 
		p19.getCategories().add(cat1); 
		p20.getCategories().add(cat1); 
		p21.getCategories().add(cat1); 
		p22.getCategories().add(cat1); 
		p23.getCategories().add(cat1); 
		p24.getCategories().add(cat1); 
		p25.getCategories().add(cat1); 
		p26.getCategories().add(cat1); 
		p27.getCategories().add(cat1); 
		p28.getCategories().add(cat1); 
		p29.getCategories().add(cat1); 
		p30.getCategories().add(cat1); 
		p31.getCategories().add(cat1); 
		p32.getCategories().add(cat1); 
		p33.getCategories().add(cat1); 
		p34.getCategories().add(cat1); 
		p35.getCategories().add(cat1); 
		p36.getCategories().add(cat1); 
		p37.getCategories().add(cat1); 
		p38.getCategories().add(cat1); 
		p39.getCategories().add(cat1); 
		p40.getCategories().add(cat1); 
		p41.getCategories().add(cat1); 
		p42.getCategories().add(cat1); 
		p43.getCategories().add(cat1); 
		p44.getCategories().add(cat1); 
		p45.getCategories().add(cat1); 
		p46.getCategories().add(cat1); 
		p47.getCategories().add(cat1); 
		p48.getCategories().add(cat1); 
		p49.getCategories().add(cat1); 
		p50.getCategories().add(cat1);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p5, p6));
		cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9, p10));
		cat7.getProducts().addAll(Arrays.asList(p11));

		p1.getCategories().addAll(Arrays.asList(cat1, cat4));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategories().addAll(Arrays.asList(cat1, cat4));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		productRepository.saveAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20, 
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38, 
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50)); 
		
		
		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");

		City c1 = new City(null, "Uberlândia", st1);
		City c2 = new City(null, "São Paulo", st2);
		City c3 = new City(null, "Campinas", st2);

		st1.getCities().addAll(Arrays.asList(c1));
		st2.getCities().addAll(Arrays.asList(c2, c3));

		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));

		Customer cus1 = new Customer(null, "Vinicius Ramos", "vinicius.yonezawa@gmail.com", "21994903007",TypeCustomer.NATURAL_PERSON, pe.encode("vini123"));
		cus1.getPhones().addAll(Arrays.asList("11990262852", "27583604"));
		
		Customer cus2 = new Customer(null, "Vinicius Yonezawa", "viinilolz@gmail.com", "84540662090",TypeCustomer.NATURAL_PERSON, pe.encode("vini123"));
		cus2.getPhones().addAll(Arrays.asList("11958227721", "27583609"));
		cus2.addProfile(Profile.ADMIN);
		
		

		Address add1 = new Address(null, "Rua Herticular", "69", "Casa com telhas", "Jordacity", "09298349", cus1, c2);
		Address add2 = new Address(null, "Rua Paselio", "96", "Portão azul", "Diadema", "09795321", cus1, c2);
		Address add3 = new Address(null, "Rua Viana", "326", "Arvore gigante", "Jordan", "09795323", cus2, c2);

		cus1.getAddress().addAll(Arrays.asList(add1, add2));
		cus2.getAddress().addAll(Arrays.asList(add3));
		
		customerRepository.saveAll(Arrays.asList(cus1, cus2));
		addressRepository.saveAll(Arrays.asList(add1, add2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Order od1 = new Order(null, sdf.parse("22/03/2021 08:00"), cus1, add1);
		Order od2 = new Order(null, sdf.parse("10/10/2021 17:00"), cus1, add2);

		Payment pay1 = new CardPayment(null, PaymentStatus.PAID, od1, 6);
		od1.setPayment(pay1);
		Payment pay2 = new TicketPayment(null, PaymentStatus.PENDING, od2, sdf.parse("13/10/2021 00:00"), null);
		od2.setPayment(pay2);

		cus1.getOrders().addAll(Arrays.asList(od1, od2));

		orderRepository.saveAll(Arrays.asList(od1, od2));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));

		OrderItem oi1 = new OrderItem(od1, p1, 0.00, 1, 2000.00);
		OrderItem oi2 = new OrderItem(od1, p3, 0.00, 2, 80.00);
		OrderItem oi3 = new OrderItem(od2, p2, 100.00, 1, 800.00);

		p1.getItems().addAll(Arrays.asList(oi1, oi2));
		p2.getItems().addAll(Arrays.asList(oi3));

		p1.getItems().addAll(Arrays.asList(oi1));
		p2.getItems().addAll(Arrays.asList(oi3));
		p3.getItems().addAll(Arrays.asList(oi2));

		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3));

	}

}
