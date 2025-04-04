package com.api.models.response;

import java.util.List;

public class LoginResponse {
	public LoginResponse() {} //default constructor for jason-data bind

	private String token;
	private String type;
	private int id;
	private String username;
	private String email;
	private List<String> roles;

	public String getToken() {
		return token;
	}

	public String getType() {
		return type;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public List<String> getRoles() {
		return roles;
	}

}
