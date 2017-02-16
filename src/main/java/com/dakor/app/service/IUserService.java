package com.dakor.app.service;

import com.dakor.app.data.entity.UserRole;
import com.dakor.app.service.dto.UserDto;

import java.util.List;

/**
 * .
 *
 * @author dkor
 */
public interface IUserService {
	List<UserDto> getUsers();

	UserDto getUserByName(String userName);

	UserDto save(UserDto user);

	void deleteById(Integer userId);
}
