package com.tweetapp.bean.tweet;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class TweetDto implements Serializable {
	private static final long serialVersionUID = -8969477625461780227L;
	
	private String _id;
	private String message;
	private String userId;
	private Date timeStamp;
	private int replies;
	private List<String> likes;
}
