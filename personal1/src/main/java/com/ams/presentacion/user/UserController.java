package com.ams.presentacion.user;

import javax.validation.Valid;

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
import org.springframework.web.server.ResponseStatusException;

import com.ams.presentacion.common.Messages;
import com.ams.presentacion.common.RequestResponse;
import com.ams.presentacion.common.exception.IdNotRequiredException;
import com.ams.presentacion.user.exception.UserNotFoundException;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	IUserService userService;

	// GET
	@GetMapping("/{id}")
	public RequestResponse getUserById(@PathVariable int id) {

		System.out.println("ID: " + id);
		UserDto responseDto;
		if (id == 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		try {
			responseDto = userService.findById(id);
		} catch (UserNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
		return new RequestResponse(HttpStatus.OK, Messages.GET, responseDto);
	}

	@GetMapping("")
	public RequestResponse getUsers() {
		return new RequestResponse(HttpStatus.OK, Messages.GET, userService.findAll());
	}

	// POST
	@PostMapping("")
	public RequestResponse createUser(@Valid @RequestBody UserDto userDto) {
		System.out.println("Creando usuario");
		UserDto responseDto = null;
		if(userDto.getId()!=0) {
			throw new IdNotRequiredException();
		}
		try {
			responseDto = userService.save(userDto);
		} catch (Exception e) {
			throw e;
		}
		return new RequestResponse(HttpStatus.CREATED, Messages.CREATED, responseDto);
	}

	@PutMapping()
	public RequestResponse updateUser(@RequestBody UserDto userDto) {
		return new RequestResponse(HttpStatus.OK, Messages.UPDATED, userService.update(userDto));
	}

	@DeleteMapping("/{id}")
	public RequestResponse deleteUser(@PathVariable int id) {
		userService.delete(id);
		return new RequestResponse(HttpStatus.OK, Messages.DELETED, id);
	}
	/*
	 * @ExceptionHandler(UserNotFoundException.class)
	 * 
	 * @ResponseStatus(HttpStatus.NOT_FOUND) <T> ExceptionResponse<T>
	 * userNotFoundHandler(UserNotFoundException ex) {
	 * System.out.println("Método de control de excepciones"); return new
	 * ExceptionResponse(HttpStatus.NOT_FOUND, ex.getMessage(), this.getClass()); }
	 * 
	 * // Handling
	 * 
	 * @SuppressWarnings({ "rawtypes", "unchecked" })
	 * 
	 * @ExceptionHandler(ResponseStatusException.class) public ExceptionResponse
	 * handleException(ResponseStatusException ex) { return new
	 * ExceptionResponse(ex.getStatus(), ex.getMessage(), this.getClass()); }
	 */
}
