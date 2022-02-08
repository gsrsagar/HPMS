package com.spring.viewModel;

public class LoginViewModel {
	
	private String email;
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LoginViewModel(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public LoginViewModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
