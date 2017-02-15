package com.dakor.app.service;

import com.dakor.app.service.dto.UserDto;

/**
 * .
 *
 * @author dkor
 */
public interface IUserService {

	UserDto getUserByName(String userName);

	UserDto save(UserDto user);

	void deleteById(Integer userId);
}
