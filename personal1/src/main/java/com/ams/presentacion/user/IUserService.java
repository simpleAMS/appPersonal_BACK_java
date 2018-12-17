package com.ams.presentacion.user;

import java.util.List;

import javassist.NotFoundException;

public interface IUserService {

	UserDto save(UserDto dto);

	List<UserDto> findAll();

	void delete(int id);

	UserDto findById(int id) throws NotFoundException;

	UserDto update(UserDto dto);

	User findByUsername(String username);
}
