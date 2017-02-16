package com.dakor.app.mvc.controller;

import com.dakor.app.data.entity.UserRole;
import com.dakor.app.mvc.model.LoginModel;
import com.dakor.app.mvc.model.UserModel;
import com.dakor.app.service.IUserService;
import com.dakor.app.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.List;

/**
 * .
 *
 * @author dkor
 */
@Controller
@RequestMapping("/app*")
public class AppController {
	@Autowired
	private IUserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView app(ModelAndView modelAndView) {
		modelAndView.setViewName("app");

		return modelAndView;
	}

	@RequestMapping(value="/users", method = RequestMethod.GET)
	public String getUsers(ModelAndView modelAndView) {
		List<UserModel> users = getUsers();
		modelAndView.addObject("users", users);

		return "users :: usersList";
	}

	private List<UserModel> getUsers() {
		List<UserModel> models = new ArrayList<>();
		userService.getUsers().forEach(user -> {
			UserModel model = new UserModel();
			model.setName(user.getUserName());
			model.setEmail(user.getEmail());
			model.setRole(user.getRole());
			models.add(model);
		});

		return models;
	};
/*	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String addNewPost(@Valid LoginModel loginModel, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "index";
		}

		return "app";
	}*/
}
