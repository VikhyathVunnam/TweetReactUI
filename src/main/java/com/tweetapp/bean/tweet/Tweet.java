package com.tweetapp.bean.tweet;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(value = "tweet")
public class Tweet implements Serializable {
	private static final long serialVersionUID = 1720083587916362523L;

	private String message;
	private String userId;
	private LocalDateTime timeStamp = LocalDateTime.now();
}
