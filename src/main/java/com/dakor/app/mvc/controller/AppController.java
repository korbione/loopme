package com.dakor.app.mvc.controller;

import com.dakor.app.data.entity.UserRole;
import com.dakor.app.mvc.model.LoginModel;
import com.dakor.app.mvc.model.UserModel;
import com.dakor.app.service.IUserService;
import com.dakor.app.service.UserDetailsService;
import com.dakor.app.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	public String getUsers(Model model) {
		model.addAttribute("users", getUsers());

		// define available roles: just with less ordinal ones are available
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserRole role = ((UserDetailsService.User) principal).role;
		List<UserRole> availableRoles = Stream.of(UserRole.values()).filter(candidate -> candidate.compareTo(role) < 0)
				.collect(Collectors.toList());
		model.addAttribute("roles", availableRoles);

		return "users :: usersList";
	}

	@RequestMapping(value="/users/save", method = RequestMethod.DELETE)
	public void removeUser(@Valid UserModel userModel, Model model) {

	}

	@RequestMapping(value="/users/save", method = RequestMethod.POST)
	public String saveUser(@Valid UserModel userModel, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "app";
		}

		UserDto dto = new UserDto();
		dto.setUserName(userModel.getName());
		dto.setEmail(userModel.getEmail());
		dto.setRole(userModel.getRole());

		userService.save(dto);

		model.addAttribute("users", getUsers());

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
