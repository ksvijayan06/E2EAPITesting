package com.api.tests;

import org.testng.annotations.Test;

import com.api.models.request.LoginRequest;
import com.api.models.response.LoginResponse;
import com.api.models.response.UserProfileResponse;
import com.api.service.AuthenticationService;
import com.api.service.UserManagementService;

import io.restassured.response.Response;

public class UserManagementTest {
	@Test
	public void getProfileInfoTest() {
		
		UserManagementService  user = new UserManagementService();
		Response res = user.getProfile(AuthenticationService.getToken("uday1234", "uday12345"));
		res.prettyPrint();
		UserProfileResponse pr = res.as(UserProfileResponse.class);
		System.out.println(pr.getEmail());
	}

}
