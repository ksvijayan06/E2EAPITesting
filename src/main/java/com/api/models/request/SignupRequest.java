package com.api.models.request;

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

	public static class Builder {
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

}
