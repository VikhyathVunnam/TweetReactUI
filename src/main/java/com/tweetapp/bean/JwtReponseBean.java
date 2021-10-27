package com.tweetapp.bean;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtReponseBean implements Serializable {
	private static final long serialVersionUID = 5371056224136006966L;

	private String token;
	private Date expireIn;
	private String userId;
}
