package com.api.tests;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.api.testData.DataSource;

public class TestData {
	@Test
	public void test() {
		HashMap<String, String> data = DataSource.getInstance().getTestData("TC_Auth_001");
		System.out.println(data);
	}

}
