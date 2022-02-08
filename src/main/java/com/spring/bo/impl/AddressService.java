package com.spring.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bo.IAddressService;
import com.spring.dao.AddressRepsoitory;
import com.spring.exceptions.AddressEmptyException;
import com.spring.exceptions.InValidNumericalException;
import com.spring.exceptions.InValidStringException;
import com.spring.pojo.Address;
import com.spring.util.Validator;

@Service
public class AddressService implements IAddressService {

	@Autowired
	AddressRepsoitory addressRepsoitory;
	
	@Override
	public List<Address> findAll() {
		return addressRepsoitory.findAll();
	}

	@Override
	public Address saveAddress(Address address) throws AddressEmptyException {
		if(!Validator.isEmptyAddress(address)) {
			return addressRepsoitory.save(address);
		}
		else {
			throw new AddressEmptyException("Invalid address data , Please provide valid adrress data");
		}
	}

	@Override
	public boolean deleteAddress(long id) throws InValidNumericalException {
		if(!Validator.isValidNumerical(id)) {
			return addressRepsoitory.deleteById(id);
		}
		else {
			throw new InValidNumericalException("Invalid address id , Please provide valid adrress id");
		}
	}

	@Override
	public Address updateAddress(Address address) throws AddressEmptyException {
		if(!(Validator.isEmptyAddress(address) && Validator.isValidNumerical(address.getId()))) {
			return addressRepsoitory.save(address);
		}
		else {
			throw new AddressEmptyException("Invalid address data , Please provide valid adrress data");
		}
	}

	@Override
	public Address findById(long addressId) throws InValidNumericalException {
		if(!Validator.isValidNumerical(addressId)) {
			return addressRepsoitory.findById(addressId);
		}
		else {
			throw new InValidNumericalException("Invalid address id , Please provide valid adrress id");
		}
	}

	@Override
	public List<Address> findByCity(String city) throws InValidStringException {
		if(!Validator.isValidString(city)) {
			return addressRepsoitory.findByCity(city);
		}
		else {
			throw new InValidStringException("Invalid city provided, Please provide valid city name");
		}
	}

	@Override
	public List<Address> findByState(String state) throws InValidStringException {
		if(!Validator.isValidString(state)) {
			return addressRepsoitory.findByState(state);
		}
		else {
			throw new InValidStringException("Invalid state provided, Please provide valid state name");
		}
	}

	@Override
	public List<Address> findByCountry(String country) throws InValidStringException {
		if(!Validator.isValidString(country)) {
			return addressRepsoitory.findByCountry(country);
		}
		else {
			throw new InValidStringException("Invalid country provided, Please provide valid country name");
		}
	}

	@Override
	public List<Address> findByzipCode(String zipCode) throws InValidStringException {
		if(!Validator.isValidString(zipCode)) {
			return addressRepsoitory.findByzipCode(zipCode);
		}
		else {
			throw new InValidStringException("Invalid zip code provided, Please provide valid zip code name");
		}
	}


}
