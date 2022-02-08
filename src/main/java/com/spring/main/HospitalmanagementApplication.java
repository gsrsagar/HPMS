package com.spring.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.spring.pojo")
@EnableJpaRepositories("com.spring.dao")
@ComponentScan({"com.spring.bo.impl", "com.spring.controllers", "com.spring.util"})
public class HospitalmanagementApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(HospitalmanagementApplication.class, args);
	}

}



/// bo is nothing but service layer 
// dao is nothing but repositroy layer - where database operations will perform
// pojo - plain old java objects - entities - hibernate will create 

/*
 * Controller is a web layer which is responsible for request response life cycle in Spring Rest api , ,
 * @Controller for IOC  , @RequestMapping to put url for that ->  
 *  @RequestMapping("api/admin")
 *  @CrossOrigin(origins ={})
 *  
 * 
 * 
 * 
 * */
