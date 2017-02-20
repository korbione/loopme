package com.dakor.app.mvc.controller;

import com.dakor.app.data.entity.UserRole;
import com.dakor.app.mvc.model.UserModel;
import com.dakor.app.service.IUserService;
import com.dakor.app.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * .
 *
 * @author dkor
 */
@Controller
@RequestMapping("/app/users")
@SuppressWarnings("unused")
public class UsersController extends AbstractController {
	private IUserService userService;

	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public String getUsers(Model model) {
		List<UserModel> users = userService.getUsers().stream().map(this::convert).collect(Collectors.toList());
		Collections.reverse(users);
		model.addAttribute("users", users);

		return "fragments/users :: users-table";
	}

	@GetMapping("/form")
	public String showDialog(@ModelAttribute("user") UserModel userModel, Model model) {
		if (userModel.getRole() == null) {
			userModel.setRole(UserRole.PUBLISHER);
		}

		return "fragments/user_dialog :: user-form";
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeUser(@PathVariable("id") int userId) {
		userService.deleteById(userId);
	}

	@PostMapping("/save")
	public String saveUser(@Valid @ModelAttribute("user") UserModel userModel, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "fragments/user_dialog :: user-form";
		}

		UserDto dto = new UserDto();
		dto.setId(userModel.getId());
		dto.setUserName(userModel.getName());
		dto.setEmail(userModel.getEmail());
		dto.setPassword(userModel.getPassword());
		dto.setRole(userModel.getRole());

		UserDto savedUser = userService.save(dto);

		model.addAttribute("users", Collections.singletonList(convert(savedUser)));

		return "fragments/users :: row";
	}

	@ModelAttribute("roles")
	public List<UserRole> createRolesList() {
		// define available roles: just with less ordinal ones are available
		UserRole role = getAppContext().getCurrentUser().getRole();

		return Stream.of(UserRole.values()).filter(candidate -> candidate.compareTo(role) < 0).collect(
				Collectors.toList());
	}

	private UserModel convert(UserDto dto) {
		UserModel model = null;
		if (dto != null) {
			model = new UserModel();
			model.setId(dto.getId());
			model.setName(dto.getUserName());
			model.setPassword(dto.getPassword());
			model.setEmail(dto.getEmail());
			model.setRole(dto.getRole());
		}

		return model;
	}
}
