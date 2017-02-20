package com.dakor.app.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * .
 *
 * @author dkor
 */
@Service
public class AppContext {

	public UserDetailsService.User getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return principal instanceof UserDetailsService.User ? (UserDetailsService.User) principal : null;
	}
}
