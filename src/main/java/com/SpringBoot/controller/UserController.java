package com.SpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.SpringBoot.model.User;
import com.SpringBoot.service.UserService;

@Controller
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public String getUsers(@RequestParam(value = "num", defaultValue = "10") int num, ModelMap model) {
		model.addAttribute("users", userService.userList(num));
		return "/users";
	}

	@GetMapping("/users/create")
	public String newUser(Model model) {
		model.addAttribute("user", new User());
		return "/create";
	}

	@PostMapping("/users")
	public String createUser(@ModelAttribute("user") User user) {
		userService.save(user);
		return "redirect:/users";
	}

	@GetMapping("/users/{id}/update")
	public String updateUser(Model model, @PathVariable("id") int id) {
		model.addAttribute("user", userService.getID(id));
		return "/update";
	}

	@PatchMapping("/users/{id}")
	public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
		userService.update(id, user);
		return "redirect:/users";
	}

	@DeleteMapping("/users/{id}")
	public String delete(@PathVariable("id") int id) {
		userService.delete(id);
		return "redirect:/users";
	}

}