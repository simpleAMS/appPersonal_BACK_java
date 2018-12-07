package com.ams.presentacion.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ams.presentacion.common.RequestResponse;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	IUserService userService;

	@GetMapping("/{id}")
	public RequestResponse getUserById(@PathVariable int id) {
		return new RequestResponse(200, "Perfecto: ", userService.findById(id));
	}
}
