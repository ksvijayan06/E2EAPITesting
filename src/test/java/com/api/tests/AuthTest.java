package com.api.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.listeners.TestListeners;
import com.api.models.request.LoginRequest;
import com.api.models.request.SignupRequest;
import com.api.models.response.LoginResponse;
import com.api.service.AuthenticationService;
import com.api.service.BaseService;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import javax.naming.ContextNotEmptyException;

@Listeners(TestListeners.class)
public class AuthTest {

	@Test(enabled = true)
	public void userLogin() {
		AuthenticationService auth = new AuthenticationService();
		LoginResponse res = auth.login(new LoginRequest("uday1234", "uday12345")).as(LoginResponse.class);
		auth.login(new LoginRequest("uday1234", "uday12345")).prettyPrint();
//		System.out.println(res.getEmail());
//		System.out.println(res.getId());
//		System.out.println(res.getToken());
//		System.out.println(res.getType());
//		System.out.println(res.getUsername());
//		System.out.println(res.getRoles());
	}

	@Test(enabled = true)
	public void accountCreationTest() {
		AuthenticationService auth = new AuthenticationService();
		SignupRequest request = SignupRequest.createNewRequest("TC_Auth_001");
//		SignupRequest.Builder signupForm = new SignupRequest.Builder();
//		SignupRequest req = signupForm.email("domik12861@gamebcs.com").firstName("vijayan").lastName("k s")
//				.password("vjn1231").username("vjn1").mobileNumber("1231231231").build();
//
		Response res = auth.signup(request);
//		res.prettyPrint();
//		System.out.println("My res :" +res.prettyPrint());

	}

	@Test(enabled = true)
	public void forgotPasswordTest() {
		AuthenticationService auth = new AuthenticationService();
		Response res = auth.forgotPassword("ksv@gmail.com");
		String exp = "{\"message\":\"If your email exists in our system, you will receive reset instructions.\"}";

		String s = res.body().asString();
		assertEquals(exp, s);
//		System.out.println(s);
		// res.prettyPrint();

	}

}
