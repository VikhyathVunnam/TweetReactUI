package com.tweetapp.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.bean.JwtReponseBean;
import com.tweetapp.bean.JwtRequestBean;
import com.tweetapp.bean.user.UserInfoBean;
import com.tweetapp.exception.TweetAppException;
import com.tweetapp.service.impl.JwtUserServiceImpl;
import com.tweetapp.utils.JwtTokenUtils;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private JwtUserServiceImpl userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtils jwtTokenUtil;

	@PostMapping("/register")
	public ResponseEntity<UserInfoBean> registerUser(@RequestBody UserInfoBean userInfo) throws TweetAppException {
		return new ResponseEntity<UserInfoBean>(userService.registerUser(userInfo), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<JwtReponseBean> getAuthToken(@RequestBody JwtRequestBean jwtRequestBean) throws Exception {
		authenticate(jwtRequestBean.getLoginId(), jwtRequestBean.getPassword());
		final UserDetails userDetails = userService.loadUserByUsername(jwtRequestBean.getLoginId());

		final String token = jwtTokenUtil.generateToken(userDetails);
		final Date expireIn = jwtTokenUtil.getExpirationDateFromToken(token);
		final String userId = jwtTokenUtil.getUsernameFromToken(token);

		return ResponseEntity.ok(new JwtReponseBean(token, expireIn, userId));

	}

	private void authenticate(String name, String pass) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(name, pass));

		} catch (Exception e) {
			throw new UsernameNotFoundException("invalid username or password");
		}
	}

	@GetMapping("/forget")
	public ResponseEntity<String> resetPassword(@RequestBody JwtRequestBean jwtRequestBean) {
		return new ResponseEntity<String>(userService.updateUser(jwtRequestBean), HttpStatus.OK);
	}
}
