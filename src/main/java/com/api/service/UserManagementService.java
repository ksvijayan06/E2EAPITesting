package com.api.service;


import io.restassured.response.Response;

public class UserManagementService extends BaseService {
	private static final String BASE_PATH = "/api/users/";

	public Response getProfile(String token) {
		setAuthorizationToken(token);
		return getRequest(BASE_PATH+"profile");
	}
}
