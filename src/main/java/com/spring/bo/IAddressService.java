package com.spring.bo;

import java.util.List;

import com.spring.exceptions.AddressEmptyException;
import com.spring.exceptions.InValidNumericalException;
import com.spring.exceptions.InValidStringException;
import com.spring.pojo.Address;
import com.spring.pojo.Doctor;

public interface IAddressService {
	
	public List<Address> findAll();
	public Address saveAddress(Address address) throws AddressEmptyException; 
	public boolean deleteAddress(long id) throws InValidNumericalException;
	public Address updateAddress(Address address) throws AddressEmptyException;
	public Address findById(long addressId) throws InValidNumericalException;
	public List<Address> findByCity(String city) throws InValidStringException; 
	public List<Address> findByState(String state) throws InValidStringException;
	public List<Address> findByCountry(String country) throws InValidStringException;	
	public List<Address> findByzipCode(String zipCode) throws InValidStringException;
}

