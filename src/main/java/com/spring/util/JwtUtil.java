package com.spring.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.*;
import com.spring.dao.UserRepository;
import com.spring.pojo.User;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	public static final String key = "ab%tYvosurYshdfkvzsadgfrssbadnb";
	public static final String ISSUER ="ADMIN_HOSPITAL";
	public static final String SUBJECT ="USER_HOSPITAL";
	public static final String SES_EMAIL = "SES_EMAIL";
	public static final String SES_PASS = "SES_PASSWORD";
	public static final String SES_TYPE = "SES_TYPE";
	
	@Autowired
	UserRepository userRepository;
	
	public String createToken(String session_email , String session_pass, String session_type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(SES_EMAIL, session_email);
		map.put(SES_PASS, session_pass);
		map.put(SES_TYPE,session_type);
	// algorithms -> 256 SSL bit encrypted -
	//	512 , 256 ,MD5
		SignatureAlgorithm signAlg = SignatureAlgorithm.HS256;
		String builder = Jwts.builder().setIssuer(ISSUER).setClaims(map).setSubject(SUBJECT)
				.setExpiration(Date.from(ZonedDateTime.now().plusMinutes(10000).toInstant()))
				.signWith(signAlg, key)
				.compact();
		return builder;
	}
	
	public User checkToken(String token , String userType) {
		Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		User user = userRepository.findByEmailAndPasswordAndUsertype(claims.get(SES_EMAIL).toString(),
				claims.get(SES_PASS).toString() ,claims.get(SES_TYPE).toString());
		System.out.println(user);
		if(userType!=null && user.getUsertype().toString().equals(userType)) {
			return user;
		}
		else {
			return null;
		}
	}
	
}
