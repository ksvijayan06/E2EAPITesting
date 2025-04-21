package com.api.tests.AuthenticationTesting.userRegistration;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.models.request.SignupRequest;

import io.restassured.response.Response;

public class TC_Auth_004 {
  @Test
  public void TC_Auth_004_Test() {
	  SignupRequest request = SignupRequest.createNewRequest(getClass().getSimpleName());
	  Response response = request.triggerRequest();
	  
	  Assert.assertEquals(response.statusCode(), 500);
	  Assert.assertEquals(response.getBody().jsonPath().getString("error"), "System Error");
	 
  }
}
