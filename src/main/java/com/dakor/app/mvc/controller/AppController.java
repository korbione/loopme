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
@RequestMapping("/app*")
@SuppressWarnings("unused")
public class AppController {

	@GetMapping
	public ModelAndView app(ModelAndView modelAndView) {
		modelAndView.setViewName("app");

		return modelAndView;
	}
}
