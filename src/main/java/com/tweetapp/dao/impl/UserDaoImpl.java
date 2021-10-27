package com.tweetapp.dao.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.tweetapp.bean.JwtRequestBean;
import com.tweetapp.bean.user.UserInfoBean;
import com.tweetapp.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Optional<UserInfoBean> findByLoginId(String loginId) {
		UserInfoBean user = mongoTemplate.findOne(Query.query(Criteria.where("loginId").is(loginId)),
				UserInfoBean.class);
		if (user != null)
			return Optional.of(user);
		return Optional.empty();
	}

	@Override
	public Optional<UserInfoBean> findByEmailId(String emailId) {
		UserInfoBean user = mongoTemplate.findOne(Query.query(Criteria.where("emailId").is(emailId)),
				UserInfoBean.class);
		if (user != null)
			return Optional.of(user);
		return Optional.empty();
	}

	@Override
	public Optional<UserInfoBean> findByContactNumber(String contactNumber) {
		UserInfoBean user = mongoTemplate.findOne(Query.query(Criteria.where("contactNumber").is(contactNumber)),
				UserInfoBean.class);
		if (user != null)
			return Optional.of(user);
		return Optional.empty();
	}

	@Override
	public void saveUser(UserInfoBean userInfoBean) {
		mongoTemplate.save(userInfoBean);
	}

	@Override
	public void updatePassword(JwtRequestBean userBean) {
		Update update = new Update();
		update.set("password", userBean.getPassword());
		mongoTemplate.updateFirst(Query.query(Criteria.where("loginId").is(userBean.getLoginId())), update,
				UserInfoBean.class);
	}

}
