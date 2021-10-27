package com.tweetapp.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class JwtRequestBean implements Serializable {
	private static final long serialVersionUID = 8110531924867030610L;

	private String loginId;
	private String password;
}
