package com.spring.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address implements Serializable {

	private static final long serialVersionUID = 4265352674204944987L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String address;
	private String city;
	private String state;
	private String country;
	private long zipCode;
	private String phoneNumber;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public long getZipcode() {
		return zipCode;
	}
	public void setZipcode(long zipcode) {
		this.zipCode = zipcode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", address=" + address + ", city=" + city + ", state=" + state + ", country="
				+ country + ", zipcode=" + zipCode + ", phoneNumber=" + phoneNumber + "]";
	}
	public Address(long id, String address, String city, String state, String country, long zipCode,
			String phoneNumber) {
		this.id = id;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
		this.phoneNumber = phoneNumber;
	}
	public Address() {
		
	}
	
	
	
	
}