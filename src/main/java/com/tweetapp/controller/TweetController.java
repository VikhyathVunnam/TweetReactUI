 package com.tweetapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.bean.tweet.LikeTweet;
import com.tweetapp.bean.tweet.ReplyTweet;
import com.tweetapp.bean.tweet.Tweet;
import com.tweetapp.bean.tweet.TweetDto;
import com.tweetapp.exception.TweetAppException;
import com.tweetapp.service.TweetService;

@RestController
@CrossOrigin
public class TweetController {

	@Autowired
	private TweetService tweetService;

	@PostMapping("/add")
	public ResponseEntity<Tweet> postTweet(@RequestBody Tweet tweet) throws TweetAppException {
		return new ResponseEntity<Tweet>(tweetService.saveTweet(tweet), HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<TweetDto>> getAllTweets() throws TweetAppException {
		return new ResponseEntity<>(tweetService.getAllTweets(), HttpStatus.OK);
	}

	@PostMapping("/reply/{tweetId}")
	public ResponseEntity<ReplyTweet> addReply(@PathVariable(name = "tweetId") String tweetId,
			@RequestBody ReplyTweet tweetReply) {
		return new ResponseEntity<>(tweetService.postReply(tweetReply, tweetId), HttpStatus.OK);
	}

	@GetMapping("/all/reply/{tweetId}")
	public ResponseEntity<List<ReplyTweet>> getAllReply(@PathVariable(name = "tweetId") String tweetId) {
		return new ResponseEntity<>(tweetService.getAllReplyByTweets(tweetId), HttpStatus.OK);
	}

	@PostMapping("/like/{tweetId}")
	public ResponseEntity<LikeTweet> likeTweet(@PathVariable(name = "tweetId") String tweetId) {
		return new ResponseEntity<LikeTweet>(tweetService.likeTweet(tweetId), HttpStatus.OK);
	}

}
