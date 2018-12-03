package com.ams.presentacion.User;

import java.util.List;

public interface IUserService {
	
	User save(UserDto user);

	List<User> findAll();

	void delete(int id);

	User findById(int id);

	UserDto update(UserDto userDto);
}
