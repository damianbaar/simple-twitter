package com.buildit.twitter.data;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.buildit.twitter.data.dto.Tweet;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TweetRepository implements ITweetRepository {
  private Stream<Tweet> tweets;

  public Stream<Tweet> getTweets(int count, int offset) {
    return tweets.skip(offset).limit(count);
  }

  public Stream<Tweet> getTweetsByAuthor(String author, int count, int offset) {
    Predicate<Tweet> matchAuthor = a -> {
      return author.equals(a.getAuthorId());
    };

    return tweets.filter(matchAuthor).skip(offset).limit(count);
  }

  // INFO: keeping in reverse order - recent on top to avoid sorting
  public void postTweet(Tweet tweet) {
    tweets.add(0, tweet);
  }
}