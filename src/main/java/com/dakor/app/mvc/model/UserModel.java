package com.dakor.app.mvc.model;

import com.dakor.app.data.entity.UserRole;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * .
 *
 * @author dkor
 */
public class UserModel extends AbstractModel {
	@NotNull
	@Size(min = 1, max = 255)
	private String name;

	@NotNull
	@Size(min = 1, max = 255)
	private String password;

	@Size(max = 255)
	private String email;

	@NotNull
	private UserRole role;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
