package com.buildit.twitter.data;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.buildit.twitter.data.dto.Tweet;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TweetRepository implements ITweetRepository {
  private List<Tweet> tweets;

  public List<Tweet> getTweets(int count, int offset) {
    return tweets.stream().skip(offset).limit(count).collect(Collectors.toList());
  }

  public List<Tweet> getTweetsByAuthor(String author, int count, int offset) {
    Predicate<Tweet> matchAuthor = a -> {
      return author.equals(a.getAuthorId());
    };

    return tweets.stream().filter(matchAuthor).skip(offset).limit(count).collect(Collectors.toList());
  }

  // INFO: keeping in reverse order - order matters - recent on top to avoid
  // sorting
  // remove this smutation
  public void postTweet(Tweet tweet) {
    tweets.add(0, tweet);
  }
}