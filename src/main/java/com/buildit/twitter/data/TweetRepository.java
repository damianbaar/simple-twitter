package com.buildit.twitter.data;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.buildit.twitter.data.dto.Tweet;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TweetRepository implements ITweetRepository {
  private Optional<Stream<Tweet>> tweets;

  public Optional<Stream<Tweet>> getTweets(int count, int offset) {
    return tweets.map(applyRange(count, offset));
  }

  public Optional<Stream<Tweet>> getTweetsByAuthor(String author, int count, int offset) {
    return tweets.map(stream -> stream.filter(matchAuthorById(author))).map(applyRange(count, offset));
  }

  Predicate<Tweet> matchAuthorById(String wantedAuthor) {
    return tweet -> wantedAuthor.equals(tweet.getAuthorId());
  }

  Function<Stream<Tweet>, Stream<Tweet>> applyRange(int count, int offset) {
    return (Stream<Tweet> stream) -> stream.skip(count).limit(offset);
  }

  // INFO: keeping in reverse order - recent on top to avoid sorting
  public void postTweet(Tweet tweet) {
    // tweets.add(0, tweet);
  }
}