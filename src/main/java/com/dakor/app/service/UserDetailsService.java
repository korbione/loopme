package com.dakor.app.service;

import com.dakor.app.data.dao.IUserDao;
import com.dakor.app.data.entity.UserEntity;
import com.dakor.app.data.entity.UserRole;
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
				details = new User(user.getName(), user.getPassword(), user.getRole());
			}
		}

		if (details == null) {
			throw new UsernameNotFoundException("An user with the name '" + name + "' is not found in the system."
					+ " Please, check your credentials");
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
		public UserRole role;

		public User(String username, String password, UserRole role) {
			super(username, password, Collections.singleton(new SimpleGrantedAuthority(Validate.notNull(role).name())));
			this.role = role;
		}

		@Override
		public String toString() {
			return String.format("User[name='%s', password='%s', role='%s']", getUsername(), getPassword(),
					getAuthorities().iterator().next().getAuthority());
		}
	}
}
