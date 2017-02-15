package com.dakor.app.mvc.model;

import javax.validation.constraints.Size;

/**
 * .
 *
 * @author dkor
 */
public class LoginModel {
	@Size(min=1, max=255, message = "login.username")
	private String username;

	@Size(min=1, max=255, message = "login.password")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
