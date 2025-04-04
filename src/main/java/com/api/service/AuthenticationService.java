package com.api.service;

import java.util.HashMap;

import com.api.models.request.LoginRequest;
import com.api.models.request.SignupRequest;
import com.api.models.response.LoginResponse;

import io.restassured.response.Response;

public class AuthenticationService extends BaseService {
	private static final String BASE_PATH = "/api/auth/";

	public Response login(LoginRequest payload) {
		return postRequest(payload, BASE_PATH + "login");
	}

	public Response signup(SignupRequest payload) {
		return postRequest(payload, BASE_PATH + "signup");
	}

	public Response forgotPassword(String userEmail) {
		HashMap<String, String> map = new HashMap<>();
		map.put("email", userEmail);
		return postRequest(map, BASE_PATH + "forgot-password");
	}
	
	public static String getToken(String username, String password) {
		return new AuthenticationService()
				.login(new LoginRequest(username, password))
				.as(LoginResponse.class)
				.getToken();
		
	}
}
