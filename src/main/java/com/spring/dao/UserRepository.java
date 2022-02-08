package com.spring.dao;

import java.util.List;

import javax.persistence.Column;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.exceptions.UserEmptyException;
import com.spring.pojo.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmailAndPassword(String email , String password);
	User findByEmail(String email);
	List<User> findByUsertype(String usertype);
	User findByEmailAndPasswordAndUsertype(String email , String password, String usertype);
	long deleteByEmail(String email);
}
