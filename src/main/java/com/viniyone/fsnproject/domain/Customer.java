package com.viniyone.fsnproject.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.viniyone.fsnproject.domain.enums.Profile;
import com.viniyone.fsnproject.domain.enums.TypeCustomer;

@Entity
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L; 

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	
	@Column(unique=true)
	private String email;
	private String cpfOrCnpj;
	private Integer type;
	
	@JsonIgnore
	private String password;
	
	@OneToMany(mappedBy = "customer", cascade=CascadeType.ALL)
	private List<Address> address = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name = "PHONE")
	private Set<String> phones = new HashSet<>();
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PROFILES")
	private Set<Integer> profiles = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="customer")
	private List<Order> orders = new ArrayList<>();
	
	
	public Customer() {
		addProfile(Profile.CUSTOMER);
	}

	public Customer(Integer id, String name, String email, String cpfOrCnpj, TypeCustomer type, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpfOrCnpj = cpfOrCnpj;
		this.type = (type == null) ? null : type.getCod();
		this.password = password;
		addProfile(Profile.CUSTOMER);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOrCnpj() {
		return cpfOrCnpj;
	}

	public void setCpfOrCnpj(String cpfOrCnpj) {
		this.cpfOrCnpj = cpfOrCnpj;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public Set<String> getPhones() {
		return phones;
	}

	public void setPhones(Set<String> phones) {
		this.phones = phones;
	}

	public TypeCustomer getType() {
		return TypeCustomer.toEnum(type);
	}

	public void setType(TypeCustomer type) {
		this.type = type.getCod();
	}
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Profile> getProfiles() { 
		return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addProfile(Profile profile) { 
		profiles.add(profile.getCod());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}





	
	
	
}
