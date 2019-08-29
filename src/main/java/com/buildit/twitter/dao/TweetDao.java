package com.buildit.twitter.dao;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.buildit.twitter.dto.Tweet;

public class TweetDao {
  private List<Tweet> tweets;

  public TweetDao(List<Tweet> tweets) {
    this.tweets = tweets;
  }

  public List<Tweet> getTweets(int count, int offset) {
    return tweets.stream().skip(offset).limit(count).collect(Collectors.toList());
  }

  public List<Tweet> getTweetsByAuthor(String author, int count, int offset) {
    Predicate<Tweet> matchAuthor = a -> {
      return author.equals(a.getAuthor().getId());
    };

    return tweets.stream().filter(matchAuthor).skip(offset).limit(count).collect(Collectors.toList());
  }

  // INFO: keeping in reverse order - order matters - recent on top to avoid
  // sorting
  public void saveTweet(Tweet tweet) {
    tweets.add(0, tweet);
  }
}