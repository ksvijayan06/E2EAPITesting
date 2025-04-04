package com.api.service;

import com.api.filters.LoggingFilters;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseService {
	private static final String BASE_URL = "http://64.227.160.186:8080";
	private RequestSpecification requestSpecification;
	BaseService(){
		requestSpecification = RestAssured.given().baseUri(BASE_URL);
	}
	
	static {
		RestAssured.filters(new LoggingFilters());
	}
	
	protected Response postRequest(Object payLoad, String endPoint) {
		return requestSpecification.contentType(ContentType.JSON)
				.body(payLoad).post(endPoint);
	}
	
	protected Response getRequest(String endpoint) {
		return requestSpecification.contentType(ContentType.JSON).get(endpoint);
	}
	
	protected void setAuthorizationToken(String token) {
		requestSpecification.header("Authorization", "Bearer "+token);
	}
}
