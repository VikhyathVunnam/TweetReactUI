package com.tweetapp.service.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tweetapp.bean.JwtRequestBean;
import com.tweetapp.bean.user.UserInfoBean;
import com.tweetapp.dao.UserDao;
import com.tweetapp.exception.UserAlreadyExistsException;

@Service
public class JwtUserServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userRepository;

	@Autowired
	private PasswordEncoder bycrypt;

	public UserInfoBean registerUser(UserInfoBean userInfo) throws UserAlreadyExistsException {
		if (userRepository.findByLoginId(userInfo.getLoginId()).isPresent()) {
			throw new UserAlreadyExistsException("Login Id already exists");
		} else if (userRepository.findByEmailId(userInfo.getEmailId()).isPresent()) {
			throw new UserAlreadyExistsException("email Id already exists");
		} else if (userRepository.findByContactNumber(userInfo.getContactNumber()).isPresent()) {
			throw new UserAlreadyExistsException("contact number already exists");
		} else {
			userInfo.setPassword(bycrypt.encode(userInfo.getPassword()));
			userRepository.saveUser(userInfo);
			return userInfo;
		}
	}

	public String updateUser(JwtRequestBean userInfo) {
		Optional<UserInfoBean> user = userRepository.findByLoginId(userInfo.getLoginId());
		if(user.isPresent()) {
			userInfo.setPassword(bycrypt.encode(userInfo.getPassword()));
			userRepository.updatePassword(userInfo);
			return "Password updated successfully";
		}
		throw new UsernameNotFoundException(userInfo.getLoginId() + " not found");
	}

	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		Optional<UserInfoBean> user = userRepository.findByLoginId(loginId);
		if (user.isPresent()) {
			return new User(user.get().getLoginId(), user.get().getPassword(), new ArrayList<>());
		}
		throw new UsernameNotFoundException(loginId + " not found");
	}

}
