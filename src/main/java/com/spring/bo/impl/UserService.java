package com.spring.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.spring.bo.IUserService;
import com.spring.dao.UserRepository;
import com.spring.exceptions.InValidStringException;
import com.spring.exceptions.UserEmptyException;
import com.spring.pojo.User;
import com.spring.util.Validator;

@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User saveUser(User user) throws UserEmptyException{
		if(!Validator.isUserEmpty(user)) {
			User result = userRepository.save(user);
			return result;
		}
		else {
			throw new UserEmptyException("User is Empty , So we cannot save user in data base");
		}
		
	}

	@Override
	public User updateUser(User user) throws UserEmptyException{
		if(!Validator.isUserEmpty(user)) {
			User result = userRepository.save(user);
			return result;
		}
		else {
			throw new UserEmptyException("User is Empty , So we cannot update user in data base");
		}
	}

	@Override
	public List<User> finAll() {
		List<User> result= userRepository.findAll();
		return result;
	}

	@Override
	public User findByEmailAndPassword(String email, String password) throws InValidStringException{
		if(!(email.isEmpty() && password.isEmpty())){
			User result= userRepository.findByEmailAndPassword(email, password);
			return result;
		}
		else {
			throw new InValidStringException("Invalid user name and password, please provide some valid data");
		}
		
	}

	@Override
	public User findByEmail(String email) throws InValidStringException{
		if(!(email.isEmpty())){
			User result= userRepository.findByEmail(email);
			return result;
		}
		else {
			throw new InValidStringException("Invalid email , Please provide some valid data for email");
		}
	}

	@Override
	public List<User> findByUsertype(String usertype) throws InValidStringException {
		if(!(usertype.isEmpty())){
			List<User> result= userRepository.findByUsertype(usertype);
			return result;
		}
		else {
			throw new InValidStringException("Given user type is invalid, So please provide valid data");
		}
	}

	@Override
	public long deleteByEmail(String email) throws InValidStringException {
		if(!(email.isEmpty())){
			long result= userRepository.deleteByEmail(email);
			return result;
		}
		else {
			throw new InValidStringException("Given user type is invalid, So please provide valid data");
		}
	}

	@Override
	public User findByEmailAndPasswordAndUsertype(String email, String password, String usertype)
			throws InValidStringException {
		if(!(email.isEmpty() && password.isEmpty() && usertype.isEmpty())){
			User result= userRepository.findByEmailAndPasswordAndUsertype(email,password,usertype);
			return result;
		}
		else {
			throw new InValidStringException("Given user type is invalid, So please provide valid data");
		}
	}

}
