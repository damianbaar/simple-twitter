package com.buildit.twitter.data;

import java.util.stream.Stream;

import com.buildit.twitter.data.dto.Tweet;

public interface ITweetRepository {
  Stream<Tweet> getTweets();

  Tweet addTweet(Tweet tweet);
}