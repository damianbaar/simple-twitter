package com.buildit.twitter.data;

import java.util.Optional;
import java.util.stream.Stream;

import com.buildit.twitter.data.dto.Tweet;

public interface ITweetRepository {
  Optional<Stream<Tweet>> getTweetsByAuthor(String author, int count, int offset);

  Optional<Stream<Tweet>> getTweets(int count, int offset);

  void postTweet(Tweet tweet);
}