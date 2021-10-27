package com.tweetapp.bean.tweet;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(value = "like")
@Data
public class LikeTweet implements Serializable {
	private static final long serialVersionUID = -4554507891230125261L;
	@Indexed(unique = true)
	private String likeBy;
	@Indexed(unique = true)
	private ObjectId tweetId;

}
