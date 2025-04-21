package com.api.tests.AuthenticationTesting.userRegistration;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.listeners.TestListeners;
import com.api.models.request.SignupRequest;
import com.api.service.AuthenticationService;

import io.restassured.response.Response;
@Listeners(TestListeners.class)
public class TC_Auth_003 {
  @Test
  public void TC_Auth_003_Test() {
	  SignupRequest request=SignupRequest.createNewRequest(getClass().getSimpleName());
	  Response response = request.triggerRequest();
	  
	  Assert.assertEquals(response.getStatusCode(), 400);
	  Assert.assertEquals(response.getBody().asPrettyString(), "Error: Email is already in use!");
  }
}
