package com.buildit.twitter.data;

import java.util.List;

import com.buildit.twitter.data.dto.Tweet;

public interface ITweetRepository {
  List<Tweet> getTweetsByAuthor(String author, int count, int offset);

  List<Tweet> getTweets(int count, int offset);

  void postTweet(Tweet tweet);
}