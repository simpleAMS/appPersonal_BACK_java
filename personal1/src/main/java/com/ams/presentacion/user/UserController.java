package com.ams.presentacion.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ams.presentacion.common.RequestResponse;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	IUserService userService;

	// GET
	@GetMapping("/{id}")
	public RequestResponse getUserById(@PathVariable int id) {
		return new RequestResponse(200, "Usuario: ", userService.findById(id));
	}

	@GetMapping("")
	public RequestResponse getUsers() {
		return new RequestResponse(200, "Usuarios: ", userService.findAll());
	}

	// POST
	@PostMapping("")
	public RequestResponse createUser(@RequestBody UserDto userDto) {
		UserDto responseDto = null;
		try {
			responseDto = userService.save(userDto);
		} catch (Exception e) {
		}
		return new RequestResponse(HttpStatus.CREATED, "Registro añadido: ", responseDto);
	}

	@PutMapping()
	public RequestResponse updateUser(@RequestBody UserDto userDto) {
		return new RequestResponse(200, "Usuario: ", userService.update(userDto));
	}

	@DeleteMapping("/{id}")
	public RequestResponse deleteUser(@PathVariable int id) {
		userService.delete(id);
		return new RequestResponse(200, "Registro eliminado: ", id);
	}
}
