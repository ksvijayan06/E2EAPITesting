package com.api.models.response;

public class UserProfileResponse {
	private String username;
	private int id;
	private String email;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	
	public UserProfileResponse() {} //default constructor for jason-data bind

	public String getUsername() {
		return username;
	}
	
	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

}
