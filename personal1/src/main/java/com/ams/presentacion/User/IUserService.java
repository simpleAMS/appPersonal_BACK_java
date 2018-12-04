package com.ams.presentacion.User;

import java.util.List;

public interface IUserService {

	UserDto save(UserDto userDto);

	List<User> findAll();

	void delete(int id);

	UserDto findById(int id);

	UserDto update(UserDto userDto);

	UserDto findByUsername(String username);
}
