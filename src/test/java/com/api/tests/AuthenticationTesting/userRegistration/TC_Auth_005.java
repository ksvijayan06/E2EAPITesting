package com.api.tests.AuthenticationTesting.userRegistration;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.listeners.TestListeners;
import com.api.models.request.SignupRequest;

import io.restassured.response.Response;

@Listeners(TestListeners.class)
public class TC_Auth_005 {
  @Test
  public void TC_Auth_005_Test() {
	  SignupRequest request = SignupRequest.createNewRequest(getClass().getSimpleName());
	  Response response = request.triggerRequest();
	  
	  Assert.assertEquals(response.statusCode(), 500);
	  	Assert.assertEquals(response.getBody().jsonPath().getString("error"), "System Error");
  }
}
