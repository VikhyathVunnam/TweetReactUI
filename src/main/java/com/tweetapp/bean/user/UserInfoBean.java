package com.tweetapp.bean.user;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(value = "user")
public class UserInfoBean implements Serializable {
	private static final long serialVersionUID = -7091553609909608L;
	
	private String firstName;
	private String lastName;
	private String loginId;
	private String emailId;
	private String password;
	private String contactNumber;
}
