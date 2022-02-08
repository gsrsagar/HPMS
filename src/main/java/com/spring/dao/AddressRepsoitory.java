package com.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.pojo.Address;

public interface AddressRepsoitory extends JpaRepository<Address, Long> {
	Address findById(long id);
	List<Address> findByCity(String city); 
	List<Address> findByState(String state);
	List<Address> findByCountry(String country);	
	List<Address> findByzipCode(String zipCode);
	boolean deleteById(long addressId);
		
}