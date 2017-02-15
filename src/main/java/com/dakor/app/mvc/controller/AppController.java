package com.dakor.app.mvc.controller;

import com.dakor.app.mvc.model.LoginModel;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * .
 *
 * @author dkor
 */
@Controller
public class AppController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView enter(ModelAndView modelAndView) {
		modelAndView.setViewName("redirect:app");

		return modelAndView;
	}

	@RequestMapping(value = "/login*", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) Object error, @RequestParam(value = "logout", required = false) Object logout,
			ModelAndView modelAndView) {
		if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
			modelAndView.setViewName("redirect:app");
		} else {
			if (error != null) {
				modelAndView.addObject("loginError", true);
			}
			if (logout != null) {
				modelAndView.addObject("logout", true);
			}
			modelAndView.setViewName("login");
		}

		return modelAndView;
	}

/*	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(ModelAndView modelAndView) {
		modelAndView.setViewName("logout");
		return modelAndView;
	}*/

	@RequestMapping(value = "/app*", method = RequestMethod.GET)
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
