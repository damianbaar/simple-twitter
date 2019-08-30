package com.buildit.twitter.data;

import java.util.List;
import java.util.stream.Stream;

import com.buildit.twitter.data.dto.Tweet;

public interface ITweetRepository {
  Stream<Tweet> getTweetsByAuthor(String author, int count, int offset);

  Stream<Tweet> getTweets(int count, int offset);

  void postTweet(Tweet tweet);
}