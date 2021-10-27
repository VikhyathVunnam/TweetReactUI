package com.tweetapp.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.tweetapp.bean.tweet.LikeTweet;
import com.tweetapp.bean.tweet.ReplyTweet;
import com.tweetapp.bean.tweet.Tweet;
import com.tweetapp.bean.tweet.TweetDto;
import com.tweetapp.dao.TweetDao;
import com.tweetapp.utils.UserUtil;

@Repository
public class TweetDaoImpl implements TweetDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Optional<Tweet> saveTweet(Tweet tweet) {
		return Optional.of(mongoTemplate.save(tweet));
	}

	@Override
	public List<TweetDto> getAllTweets() {

		LookupOperation replyLookUp = LookupOperation.newLookup().from("reply").localField("_id")
				.foreignField("tweetId").as("replies");

		LookupOperation likeLookUp = LookupOperation.newLookup().from("like").localField("_id").foreignField("tweetId")
				.as("likes");

		Aggregation ag = Aggregation.newAggregation(replyLookUp, likeLookUp,
				Aggregation.project("message", "timeStamp", "userId").and("replies").size().as("replies").and("likes").as("likes"));

		List<TweetDto> tweets = mongoTemplate.aggregate(ag, "tweet", TweetDto.class).getMappedResults();

		return tweets;
	}

	@Override
	public ReplyTweet saveReply(ReplyTweet reply) {
		reply.setUserId(UserUtil.getUserName());
		return mongoTemplate.save(reply);
	}

	@Override
	public List<ReplyTweet> getAllReply(String tweetId) {
		return mongoTemplate.find(Query.query(Criteria.where("tweetId").is(tweetId)), ReplyTweet.class);
	}

	@Override
	public LikeTweet saveLike(LikeTweet likeTweet) {
		return mongoTemplate.save(likeTweet);
	}

	@Override
	public List<LikeTweet> getAllLikesByUser(String userId) {
		return mongoTemplate.find(Query.query(Criteria.where("likeBy").is(userId)), LikeTweet.class);
	}

}
