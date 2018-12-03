package com.ams.presentacion.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ams.presentacion.User.User;
import com.ams.presentacion.User.UserService;
import com.ams.presentacion.common.RequestResponse;
import com.ams.presentacion.security.JwtTokenUtil;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/generate-token", method = RequestMethod.POST)
	public RequestResponse register(@RequestBody ApplicationUser appUser) throws AuthenticationException {

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));
		/*
		final User user = userService.findOne(appUser.getUsername());
		final String token = jwtTokenUtil.generateToken(user);
		return new RequestResponse(200, "success", new AuthToken(token, user.getUsername()));
		*/
		return new RequestResponse(200, "success", null);
	}
}
