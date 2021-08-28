package com.viniyone.fsnproject.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viniyone.fsnproject.domain.Order;
import com.viniyone.fsnproject.domain.OrderItem;
import com.viniyone.fsnproject.domain.TicketPayment;
import com.viniyone.fsnproject.domain.enums.PaymentStatus;
import com.viniyone.fsnproject.repositories.OrderItemRepository;
import com.viniyone.fsnproject.repositories.OrderRepository;
import com.viniyone.fsnproject.repositories.PaymentRepository;
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
	
	
	public Order find(Integer id) { 
		  Optional<Order> obj = repo.findById(id); 
		  return obj.orElseThrow(() -> new ObjectNotFoundException( 
		      "Object not found! Id: " + id + ", Type: " + Order.class.getName())); 
		} 
	
	@Transactional
	public Order insert(Order obj) {
		obj.setId(null);
		obj.setInstant(new Date());
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
			oi.setPrice(productService.find(oi.getProduct().getId()).getPrice());
			oi.setOrder(obj);
		}
		orderItemRepository.saveAll(obj.getItems());
		return obj;
	}
	
}
