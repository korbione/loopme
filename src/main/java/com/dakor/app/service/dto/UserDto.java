package com.dakor.app.service.dto;

import com.dakor.app.data.entity.UserRole;

/**
 * .
 *
 * @author dkor
 */
public class UserDto extends AbstractIdentifierDto {
	private String userName;
	private String password;
	private String email;
	private UserRole role;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
