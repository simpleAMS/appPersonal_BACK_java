package com.ams.presentacion.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ams.presentacion.common.RequestResponse;
import com.ams.presentacion.security.JwtTokenUtil; 
import com.ams.presentacion.user.User;
import com.ams.presentacion.user.UserService;

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

	@PostMapping(value = "/generate-token")
	public RequestResponse register(@RequestBody ApplicationUser appUser) throws AuthenticationException {

		authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));
		final User user = userService.findByUsername(appUser.getUsername());
		final String token = jwtTokenUtil.generateToken(user);
		return new RequestResponse(HttpStatus.OK, "success", new AuthToken(token, user.getUsername()));
	}
}
