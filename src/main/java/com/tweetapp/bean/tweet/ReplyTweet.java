package com.tweetapp.bean.tweet;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(value = "reply")
@Data
public class ReplyTweet {

	private String message;
	private ObjectId tweetId;
	private String userId;
	private LocalDateTime timeStamp = LocalDateTime.now();
}
