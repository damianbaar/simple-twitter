package com.buildit.twitter.data;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.buildit.twitter.data.dto.Tweet;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TweetRepository implements ITweetRepository {
  private Supplier<Optional<Stream<Tweet>>> tweets;

  @Autowired
  private Helpers helpers;

  public Supplier<Optional<Stream<Tweet>>> source() {
    return () -> Optional.of(tweets.get().orElse(Stream.<Tweet>of()));
  }

  public Optional<Stream<Tweet>> getTweets(int count, int offset) {
    return source().get().map(applyRange(count, offset));
  }

  public Optional<Stream<Tweet>> getTweetsByAuthor(String author, int count, int offset) {
    return source().get().map(stream -> stream.filter(matchAuthorById(author))).map(applyRange(count, offset));
  }

  Predicate<Tweet> matchAuthorById(String wantedAuthor) {
    return tweet -> wantedAuthor.equals(tweet.getAuthorId());
  }

  Function<Stream<Tweet>, Stream<Tweet>> applyRange(int count, int offset) {
    return (Stream<Tweet> stream) -> stream.skip(offset).limit(count);
  }

  public Supplier<Optional<Stream<Tweet>>> add(Tweet tweet) {
    tweets = helpers.add(tweet, source());
    return tweets;
  }
}