package com.api.reporting;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;

public class ExtentReportManager {
	private static ExtentReports extentReports;
	private static ThreadLocal<ExtentTest> tesThreadLocal = new ThreadLocal<>();

	private static ExtentReports createInstance() {
		if (extentReports == null) {
			//synchronized (ExtentReportManager.class) {
				//if(extentReports == null) {
					String fileName = generateReportFileName();
					// spark report help to create html report
					ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("test-output/reports/" + fileName);
					extentSparkReporter.config().setTheme(Theme.DARK);
					extentSparkReporter.config().setDocumentTitle("Rest Api test results");

					// then we add the html reporting our extent report
					extentReports = new ExtentReports();
					extentReports.attachReporter(extentSparkReporter);
					extentReports.setSystemInfo("Environment", "Test");
					extentReports.setSystemInfo("User ", System.getProperty("user.name")); // user.name retrive the username we used in our laptop 'vijayan.s01'
			//	}
			//}
		}
		return extentReports;
	}

	private static String generateReportFileName() {
		DateTimeFormatter dateTimeFormatter =DateTimeFormatter.ofPattern("dd-MM-YYY_HHmmss");
		return "APITestReport"+dateTimeFormatter.format(LocalDateTime.now())+".html";
	}
	
	public static void startTest(String testName) {
		ExtentTest test=createInstance().createTest(testName);
		tesThreadLocal.set(test);
	}

	public static void logRequest(FilterableRequestSpecification requestSpecification) {
		StringBuilder requestSpec=new StringBuilder();
		requestSpec.append("<pre>");
		requestSpec.append("Request Method : ").append(requestSpecification.getMethod()).append("\n");
		requestSpec.append("Request URI : ").append(requestSpecification.getURI()).append("\n");
		requestSpec.append("Request Headers : ").append("\n");
		
		for(Header header:requestSpecification.getHeaders()) {
			requestSpec.append(" ").append(header.getName()).append(": ").append(header.getValue()).append("\n");
		}
		requestSpec.append("Request Body : ").append("\n");
		if(requestSpecification.getBody()!=null) {
			requestSpec.append(requestSpecification.getBody().toString());
		}
		else {
			requestSpec.append("{ }");
		}
		requestSpec.append("</pre>");
		tesThreadLocal.get().log(Status.INFO, "Request Details : "+requestSpec.toString());
	}
	
	public static void logResponse(Response response) {
		StringBuilder responseDetails=new StringBuilder();
		responseDetails.append("<pre>");
		responseDetails.append("Response Status : ").append(response.getStatusCode()).append("\n");
		responseDetails.append("Response Headers : ").append("\n");
		
		response.getHeaders().forEach(header->
		responseDetails.append(" ").append(header.getName()).append(": ").append(header.getValue()).append("\n"));
		
		
		responseDetails.append("Response Body : ").append("\n");
		responseDetails.append(response.asPrettyString());		
		responseDetails.append("</pre>");
		
		tesThreadLocal.get().log(Status.INFO, "Response Details : "+responseDetails.toString());
		
		if(response.getStatusCode()>=200 && response.getStatusCode()<300) {
			tesThreadLocal.get().pass("Request completed Successfully");
		}
		else {
			tesThreadLocal.get().fail("Request Failed with Status code : "+ response.getStatusCode());
		}
	}
	
	public static void endTest() {
		if(tesThreadLocal.get()!=null) {
			extentReports.flush();
		}
	}
	
	public static void saveReport() {
		if(extentReports != null) extentReports.flush();
	}
	
	static ExtentTest getTest() {
		return tesThreadLocal.get();
	}
}
