package com.dakor.app.mvc.controller;

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
@RequestMapping("/app*")
@SuppressWarnings("unused")
public class AppController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView app(ModelAndView modelAndView) {
		modelAndView.setViewName("app");

		return modelAndView;
	}

/*	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String addNewPost(@Valid LoginModel loginModel, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "index";
		}

		return "app";
	}*/
}
