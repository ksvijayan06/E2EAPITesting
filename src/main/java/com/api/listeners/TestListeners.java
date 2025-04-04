package com.api.listeners;

import org.apache.logging.log4j.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ITestListener {
	private static final Logger logger = LogManager.getLogger(TestListeners.class);

	@Override
	public void onTestStart(ITestResult result) {
		logger.info("Test start: {}", result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		logger.info("Test Failure: {}", result.getName());
		logger.error("Exception: ", result.getThrowable());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info("Test Success: {}", result.getName());
	}

	public void onStart(ITestContext context) {
		

	}

	public void onFinish(ITestContext context) {

	}
}
