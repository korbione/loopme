package com.dakor.app.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * .
 *
 * @author dkor
 */
@Controller
@RequestMapping("/login")
@SuppressWarnings("unused")
public class LoginController extends AbstractController {

	@GetMapping
	public ModelAndView login(ModelAndView modelAndView) {
		if (getAppContext().getCurrentUser() != null) {
			modelAndView.setViewName("redirect:app");
		} else {
			modelAndView.setViewName("login");
		}

		return modelAndView;
	}
}
