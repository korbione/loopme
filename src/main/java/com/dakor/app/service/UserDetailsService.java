package com.dakor.app.service;

import com.dakor.app.data.dao.IUserDao;
import com.dakor.app.data.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * .
 *
 * @author dkor
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		UserDetails details = null;

		if (StringUtils.isNotBlank(name)) {
			UserEntity user = userDao.findByName(name);
			if (user != null) {
				details = new User(user.getName(), user.getPassword(), user.getRole().name());
			}
		}

		log.info("Logging for the user: " + details);

		return details;
	}

	/**
	 * .
	 *
	 * @author dkor
	 */
	public static class User extends org.springframework.security.core.userdetails.User {

		public User(String username, String password, String role) {
			super(username, password, Collections.singleton(new SimpleGrantedAuthority(Validate.notNull(role))));
		}

		@Override
		public String toString() {
			return String.format("User[name='%s', password='%s', role='%s']", getUsername(), getPassword(),
					getAuthorities().iterator().next().getAuthority());
		}
	}
}
