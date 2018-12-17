package com.ams.presentacion.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ams.presentacion.user.exception.UserNotFounException;

import javassist.NotFoundException;

@Service(value = "userService")
public class UserService implements IUserService, UserDetailsService {

	@Autowired
	ModelMapper mapper;
	@Autowired
	UserDao userDao;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	private void configureMapping() {
		mapper.getConfiguration().isSkipNullEnabled();
	}

	@Override
	public UserDto findById(int id) throws NotFoundException {
		Optional<User> optional = userDao.findById(id);
		UserDto dto = null;
		User entity = optional.isPresent() ? optional.get() : null;
		if (entity == null) {
			throw new UserNotFounException((long) id);
		}
		try {
			dto = convertToDto(entity);
		} catch (Exception e) {
			throw e;
			// log
		}
		return dto;
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
	public UserDto save(UserDto dto) {
		// Encrypt password
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		User entity = convertToEntity(dto);
		entity = userDao.save(entity);
		return convertToDto(entity);
	}

	@Override
	public void delete(int id) {
		userDao.deleteById(id);
	}

	@Override
	public UserDto update(UserDto dto) {
		User entity = convertToEntity(dto);
		userDao.save(entity);
		return dto;
	}

	@Override
	public User findByUsername(String username) {
		Optional<User> optionalUser = userDao.findByUsername(username);
		return optionalUser.isPresent() ? optionalUser.get() : null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user;
		Optional<User> optionalUser = null;
		try {
			optionalUser = userDao.findByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		user = optionalUser.isPresent() ? optionalUser.get() : null;
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

	// Clases para mapeo

	private UserDto convertToDto(User user) {
		UserDto userDto = null;
		try {
			userDto = mapper.map(user, UserDto.class);
		} catch (Exception e) {
			System.out.println("Error " + e);
		}

		return userDto;
	}

	private User convertToEntity(UserDto dto) throws ParseException {
		User user = null;
		try {
			user = mapper.map(dto, User.class);
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
		return user;
	}

}
