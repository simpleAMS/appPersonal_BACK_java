package com.ams.presentacion.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ams.presentacion.common.ControllerMessages;
import com.ams.presentacion.common.RequestResponse;
import com.ams.presentacion.user.exception.UserNotFounException;

import javassist.NotFoundException;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	IUserService userService;

	// GET
	@GetMapping("/{id}")
	public RequestResponse getUserById(@PathVariable int id) {
		UserDto responseDto;
		try {
			responseDto = userService.findById(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Register not found", e);
		}
		return new RequestResponse(HttpStatus.OK, ControllerMessages.GET, responseDto);
	}

	@GetMapping("")
	public RequestResponse getUsers() {
		return new RequestResponse(HttpStatus.OK, ControllerMessages.GET, userService.findAll());
	}

	// POST
	@PostMapping("")
	public RequestResponse createUser(@RequestBody UserDto userDto) {
		UserDto responseDto = null;
		try {
			responseDto = userService.save(userDto);
		} catch (Exception e) {
		}
		return new RequestResponse(HttpStatus.CREATED,ControllerMessages.CREATED, responseDto);
	}

	@PutMapping()
	public RequestResponse updateUser(@RequestBody UserDto userDto) {
		return new RequestResponse(HttpStatus.OK, ControllerMessages.UPDATED, userService.update(userDto));
	}

	@DeleteMapping("/{id}")
	public RequestResponse deleteUser(@PathVariable int id) {
		userService.delete(id);
		return new RequestResponse(HttpStatus.OK, ControllerMessages.DELETED, id);
	}

	/*
	// Handling
	@ExceptionHandler({ Exception.class })
	public RequestResponse handleException() {
		return new RequestResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno", null);
	}
*/
	
	@ExceptionHandler(UserNotFounException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String userNotFoundHandler(UserNotFounException ex) {
		System.out.println("Método de control de excepciones");
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex.getCause());
	}
}
