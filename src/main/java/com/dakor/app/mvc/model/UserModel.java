package com.dakor.app.mvc.model;

import com.dakor.app.data.entity.UserRole;

/**
 * .
 *
 * @author dkor
 */
public class UserModel {
	private String name;
	private String email;
	private UserRole role;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
}
