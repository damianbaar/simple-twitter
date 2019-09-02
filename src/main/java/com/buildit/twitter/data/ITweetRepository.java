package com.buildit.twitter.data;

import java.util.stream.Stream;

import com.buildit.twitter.data.dto.Tweet;

public interface ITweetRepository {
  Tweet addTweet(Tweet tweet);

  Stream<Tweet> getTweets();

  Stream<Tweet> getTweetsById(String authorId);
}