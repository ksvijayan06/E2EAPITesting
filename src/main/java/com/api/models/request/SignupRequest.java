package com.api.models.request;

import java.util.HashMap;

import com.api.service.AuthenticationService;
import com.api.testData.DataSource;

import io.restassured.response.Response;

public class SignupRequest {
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String mobileNumber;

	private SignupRequest(Builder builder) {
		this.username = builder.username;
		this.password = builder.password;
		this.email = builder.email;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.mobileNumber = builder.mobileNumber;
	}

	private static class Builder {

		private String username;
		private String password;
		private String email;
		private String firstName;
		private String lastName;
		private String mobileNumber;

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder mobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
			return this;
		}

		public SignupRequest build() {
			return new SignupRequest(this);
		}
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public static SignupRequest createNewRequest(String testCase) {
		HashMap<String, String> map = DataSource.getInstance().getTestData(testCase);
		Builder builder = new Builder();
		return builder.email(map.get("email")).firstName(map.get("firstName")).lastName(map.get("lastName"))
				.mobileNumber(map.get("mobileNumber")).username(map.get("username"))
				.password(map.get("password")).build();
	}
	
	public Response triggerRequest() {
		AuthenticationService auth = new AuthenticationService();
		return auth.signup(this);
	}

}
