package com.buildit.twitter;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TweetDao {
  private List<Tweet> tweets;

  public TweetDao(List<Tweet> tweets) {
    this.tweets = tweets;
  }

  public List<Tweet> getTweets(int count, int offset) {
    return tweets.stream().skip(offset).limit(count).collect(Collectors.toList());
  }

  public List<Tweet> getTweetsByAuthor(String author) {
    Predicate<Tweet> matchAuthor = a -> {
      return author.equals(a.getAuthor().getId());
    };

    return tweets.stream().filter(matchAuthor).collect(Collectors.toList());
  }

  // INFO: keeping in reverse order - order matters - recent on top to avoid
  // sorting
  public void saveTweet(Tweet tweet) {
    tweets.add(0, tweet);
  }
}