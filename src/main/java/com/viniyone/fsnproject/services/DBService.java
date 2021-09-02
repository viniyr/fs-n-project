package com.viniyone.fsnproject.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
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
		Category cat3 = new Category(null, "Home, Bed & Bath,");
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

		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");

		City c1 = new City(null, "Uberlândia", st1);
		City c2 = new City(null, "São Paulo", st2);
		City c3 = new City(null, "Campinas", st2);

		st1.getCities().addAll(Arrays.asList(c1));
		st2.getCities().addAll(Arrays.asList(c2, c3));

		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));

		Customer cus1 = new Customer(null, "Anya Yonoshi", "vinicius.yonezawa@gmail.com", "43910937705",
				TypeCustomer.NATURAL_PERSON);

		cus1.getPhones().addAll(Arrays.asList("11990262852", "27583604"));

		Address add1 = new Address(null, "Rua Herticular", "69", "Casa com telhas", "Jordacity", "09298349", cus1, c2);
		Address add2 = new Address(null, "Rua Paselio", "96", "Portão azul", "Diadema", "09795321", cus1, c2);

		cus1.getAddress().addAll(Arrays.asList(add1, add2));

		customerRepository.saveAll(Arrays.asList(cus1));
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
