package com.ams.presentacion.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User,Integer>{

	public Optional<User> findByUsername(String username);

}
