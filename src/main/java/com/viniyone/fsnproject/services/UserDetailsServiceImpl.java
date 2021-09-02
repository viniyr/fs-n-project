package com.viniyone.fsnproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.viniyone.fsnproject.domain.Customer;
import com.viniyone.fsnproject.repositories.CustomerRepository;
import com.viniyone.fsnproject.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired 
	private CustomerRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { 
		Customer cus = repo.findByEmail(email);
		if ( cus == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new UserSS(cus.getId(), cus.getEmail(), cus.getPassword(),cus.getProfiles());
	}
	
}
