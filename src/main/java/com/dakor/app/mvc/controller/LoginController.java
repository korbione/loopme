package com.dakor.app.mvc.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * .
 *
 * @author dkor
 */
@Controller
@RequestMapping("/login")
@SuppressWarnings("unused")
public class LoginController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView login(ModelAndView modelAndView) {
		if (isAuthenticated()) {
			modelAndView.setViewName("redirect:app");
		} else {
			modelAndView.setViewName("login");
		}

		return modelAndView;
	}

	private boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null && !(authentication instanceof AnonymousAuthenticationToken);
	}
}
