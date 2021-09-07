package com.viniyone.fsnproject.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.viniyone.fsnproject.domain.Customer;
import com.viniyone.fsnproject.domain.Order;
import com.viniyone.fsnproject.domain.OrderItem;
import com.viniyone.fsnproject.domain.TicketPayment;
import com.viniyone.fsnproject.domain.enums.PaymentStatus;
import com.viniyone.fsnproject.repositories.OrderItemRepository;
import com.viniyone.fsnproject.repositories.OrderRepository;
import com.viniyone.fsnproject.repositories.PaymentRepository;
import com.viniyone.fsnproject.security.UserSS;
import com.viniyone.fsnproject.services.exceptions.AuthorizationException;
import com.viniyone.fsnproject.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;

	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private EmailService emailService;
	
	public Order find(Integer id) { 
		  Optional<Order> obj = repo.findById(id); 
		  return obj.orElseThrow(() -> new ObjectNotFoundException( 
		      "Object not found! Id: " + id + ", Type: " + Order.class.getName())); 
		} 
	
	@Transactional
	public Order insert(Order obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.setCustomer(customerService.find(obj.getCustomer().getId()));
		obj.getPayment().setStatus(PaymentStatus.PENDING);
		obj.getPayment().setOrder(obj);
		if (obj.getPayment() instanceof TicketPayment) { 
			TicketPayment paym = (TicketPayment) obj.getPayment();
			ticketService.fillTicketPayment(paym, obj.getInstant());
		}
		obj = repo.save(obj);
		paymentRepository.save(obj.getPayment());
		for (OrderItem oi : obj.getItems()) {
			oi.setDiscount(0.0);
			oi.setProduct(productService.find(oi.getProduct().getId()));
			oi.setPrice(oi.getProduct().getPrice());
			oi.setOrder(obj);
		}
		orderItemRepository.saveAll(obj.getItems());
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}

	public Page<Order> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Access denied");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Customer customer = customerService.find(user.getId());
		return repo.findByCustomer(customer,pageRequest);
	}
	
	
}
