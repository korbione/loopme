package com.dakor.app.mvc.controller;

import com.dakor.app.data.entity.UserRole;
import com.dakor.app.mvc.model.UserModel;
import com.dakor.app.service.IUserService;
import com.dakor.app.service.UserDetailsService;
import com.dakor.app.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
public class UsersController {
    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
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

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeUser(@PathVariable("id") int userId) {
        userService.deleteById(userId);
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
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
            model.setId(user.getId());
            model.setName(user.getUserName());
            model.setEmail(user.getEmail());
            model.setRole(user.getRole());
            models.add(model);
        });

        return models;
    }
}
