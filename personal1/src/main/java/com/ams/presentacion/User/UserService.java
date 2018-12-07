package com.ams.presentacion.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service(value = "userService")
public class UserService implements IUserService, UserDetailsService {

	@Autowired
	ModelMapper modelMapper;
	@Autowired
	UserDao userDao;

	@Override
	public UserDto save(UserDto dto) {
		User entity = convertToEntity(dto);
		entity = userDao.save(entity);
		return convertToDto(entity);
	}

	@Override
	public List<UserDto> findAll() {
		Iterable<User> iterable = userDao.findAll();
		List<UserDto> list = new ArrayList<UserDto>();
		iterable.iterator().forEachRemaining(t -> {
			UserDto dto = convertToDto(t);
			list.add(dto);
		});
		return list;
	}

	@Override
	public void delete(int id) {
		userDao.deleteById(id);

	}

	@Override
	public UserDto findById(int id) {
		Optional<User> optionalDto = userDao.findById(id);
		return optionalDto.isPresent() ? convertToDto(optionalDto.get()) : null;
	}

	@Override
	public UserDto update(UserDto dto) {
		User entity = convertToEntity(dto);
		userDao.save(entity);
		return dto;
	}

	@Override
	public UserDto findByUsername(String username) {
		Optional<User> optionalUser = userDao.findByUsername(username);
		return optionalUser.isPresent() ? convertToDto(optionalUser.get()) : null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = userDao.findByUsername(username);
		User user = optionalUser.isPresent() ? optionalUser.get() : null;
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthority());
	}

	// Temporal mientras no se añaden los roles a BD
	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	private UserDto convertToDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
		return userDto;
	}

	private User convertToEntity(UserDto postDto) throws ParseException {
		User user = modelMapper.map(postDto, User.class);

		return user;
	}
}
