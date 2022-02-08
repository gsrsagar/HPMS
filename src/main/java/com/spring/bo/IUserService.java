package com.spring.bo;

import java.util.List;

import com.spring.exceptions.InValidStringException;
import com.spring.exceptions.UserEmptyException;
import com.spring.pojo.User;

public interface IUserService {
	
	public User saveUser(User user) throws UserEmptyException;
	public User updateUser(User user) throws UserEmptyException;
	public List<User> finAll() throws UserEmptyException;
	public User findByEmailAndPassword(String email , String password) throws InValidStringException;
	public User findByEmail(String email) throws InValidStringException;
	public List<User> findByUsertype(String usertype) throws InValidStringException;
	public long deleteByEmail(String email) throws InValidStringException;
	public User findByEmailAndPasswordAndUsertype(String email , String password, String usertype) throws InValidStringException;
}
