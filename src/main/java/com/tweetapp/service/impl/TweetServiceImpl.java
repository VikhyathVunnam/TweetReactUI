package com.tweetapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.bean.tweet.LikeTweet;
import com.tweetapp.bean.tweet.ReplyTweet;
import com.tweetapp.bean.tweet.Tweet;
import com.tweetapp.bean.tweet.TweetDto;
import com.tweetapp.dao.TweetDao;
import com.tweetapp.exception.TweetAppException;
import com.tweetapp.service.TweetService;
import com.tweetapp.utils.UserUtil;

@Service
public class TweetServiceImpl implements TweetService {

	@Autowired
	private TweetDao tweetRepository;

	@Override
	public Tweet saveTweet(Tweet tweet) throws TweetAppException {
		tweet.setUserId(UserUtil.getUserName());
		Optional<Tweet> tweetData = tweetRepository.saveTweet(tweet);
		if (tweetData.isPresent()) {
			return tweetData.get();
		}
		throw new TweetAppException("Something went wrong while posting tweet");
	}

	@Override
	public List<TweetDto> getAllTweets() {
		return tweetRepository.getAllTweets();
	}

	@Override
	public ReplyTweet postReply(ReplyTweet tweetReply, String tweetId) {
		tweetReply.setTweetId(new ObjectId(tweetId));
		return tweetRepository.saveReply(tweetReply);
	}

	@Override
	public List<ReplyTweet> getAllReplyByTweets(String tweetId) {
		return tweetRepository.getAllReply(tweetId);
	}

	@Override
	public LikeTweet likeTweet(String tweetId) {
		LikeTweet like = new LikeTweet();
		like.setTweetId(new ObjectId(tweetId));
		like.setLikeBy(UserUtil.getUserName());
		return tweetRepository.saveLike(like);
	}

	@Override
	public List<LikeTweet> getAllLikesByUser(String userId) {
		return tweetRepository.getAllLikesByUser(userId);
	}

}
