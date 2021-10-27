package com.tweetapp.dao;

import java.util.List;
import java.util.Optional;

import com.tweetapp.bean.tweet.LikeTweet;
import com.tweetapp.bean.tweet.ReplyTweet;
import com.tweetapp.bean.tweet.Tweet;
import com.tweetapp.bean.tweet.TweetDto;

public interface TweetDao {
	Optional<Tweet> saveTweet(Tweet tweet);

	List<TweetDto> getAllTweets();

	ReplyTweet saveReply(ReplyTweet reply);

	List<ReplyTweet> getAllReply(String tweetId);

	LikeTweet saveLike(LikeTweet likeTweet);
	
	List<LikeTweet> getAllLikesByUser(String userId);
}
