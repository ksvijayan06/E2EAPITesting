package com.api.tests.AuthenticationTesting.userRegistration;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.listeners.TestListeners;
import com.api.models.request.SignupRequest;
import com.api.service.AuthenticationService;

import io.restassured.response.Response;
@Listeners(TestListeners.class)
public class TC_Auth_001 {

	@Test
	public void TC_Auth_001_Test() {
		SignupRequest req = SignupRequest.createNewRequest(getClass().getSimpleName());
		Response response = req.triggerRequest();

		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getBody().asPrettyString(), "User registered successfully!");
	}

}
