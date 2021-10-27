package com.tweetapp.service;

import java.util.List;

import com.tweetapp.bean.tweet.LikeTweet;
import com.tweetapp.bean.tweet.ReplyTweet;
import com.tweetapp.bean.tweet.Tweet;
import com.tweetapp.bean.tweet.TweetDto;
import com.tweetapp.exception.TweetAppException;

public interface TweetService {
	Tweet saveTweet(Tweet tweet) throws TweetAppException;

	List<TweetDto> getAllTweets();

	ReplyTweet postReply(ReplyTweet tweetReply, String tweetId);

	List<ReplyTweet> getAllReplyByTweets(String tweetId);

	LikeTweet likeTweet(String tweetId);
	
	List<LikeTweet> getAllLikesByUser(String userId);
}
