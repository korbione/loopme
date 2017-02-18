package com.dakor.app.service.impl;

import com.dakor.app.data.dao.IUserDao;
import com.dakor.app.data.entity.UserEntity;
import com.dakor.app.data.entity.UserRole;
import com.dakor.app.service.IUserService;
import com.dakor.app.service.UserDetailsService;
import com.dakor.app.service.dto.UserDto;
import com.dakor.app.service.impl.assembler.IUserAssembler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 *
 * @author dkor
 */
@Service
@SuppressWarnings("unused")
class UserService implements IUserService {
	@Autowired
	private IUserDao userDao;

	@Autowired
	private IUserAssembler userAssembler;

	@Transactional(readOnly = true)
	@Override
	public List<UserDto> getUsers() {
		List<UserDto> users = new ArrayList<>();

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserRole role = ((UserDetailsService.User) principal).role;
		userDao.findUsers(role).forEach(user -> users.add(userAssembler.assembly(user)));

		return users;
	}

	@Transactional(readOnly = true)
	@Override
	public UserDto getUserByName(String userName) {
		UserDto user = null;
		if (StringUtils.isNotBlank(userName)) {
			UserEntity userEntity = userDao.findByName(userName);
			user = userAssembler.assembly(userEntity);
		}

		return user;
	}

	@Transactional
	@Override
	public UserDto save(UserDto user) {
		UserEntity userEntity = userAssembler.assembly(user);
		if (userEntity != null) {
			UserEntity savedUserEntity = userDao.save(userEntity);
			user = userAssembler.assembly(savedUserEntity);
		}

		return user;
	}

	@Transactional
	@Override
	public void deleteById(Integer userId) {
		if (userId != null) {
			userDao.delete(userId);
		}
	}
}
