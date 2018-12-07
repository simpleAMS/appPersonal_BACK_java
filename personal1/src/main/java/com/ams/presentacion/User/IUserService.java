package com.ams.presentacion.User;

import java.util.List;

public interface IUserService {

	UserDto save(UserDto dto);

	List<UserDto> findAll();

	void delete(int id);

	UserDto findById(int id);

	UserDto update(UserDto dto);

	UserDto findByUsername(String username);
}
