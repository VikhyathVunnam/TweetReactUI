package com.tweetapp.dao;

import java.util.Optional;

import com.tweetapp.bean.JwtRequestBean;
import com.tweetapp.bean.user.UserInfoBean;

public interface UserDao {

	Optional<UserInfoBean> findByLoginId(String loginId);

	Optional<UserInfoBean> findByEmailId(String emailId);

	Optional<UserInfoBean> findByContactNumber(String contactNumber);
	
	void saveUser(UserInfoBean userInfoBean);
	
	void updatePassword(JwtRequestBean userInfo);
}
