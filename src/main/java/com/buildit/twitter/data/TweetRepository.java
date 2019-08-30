package com.buildit.twitter.data;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.buildit.twitter.data.dto.Tweet;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TweetRepository implements ITweetRepository {
  private Supplier<Optional<Stream<Tweet>>> tweets;

  private Supplier<Optional<Stream<Tweet>>> tweetsSource() {
    return () -> Optional.of(tweets.get().orElse(Stream.<Tweet>of()));
  }

  public Optional<Stream<Tweet>> getTweets(int count, int offset) {
    return tweetsSource().get().map(applyRange(count, offset));
  }

  public Optional<Stream<Tweet>> getTweetsByAuthor(String author, int count, int offset) {
    return tweetsSource().get().map(stream -> stream.filter(matchAuthorById(author))).map(applyRange(count, offset));
  }

  Predicate<Tweet> matchAuthorById(String wantedAuthor) {
    return tweet -> wantedAuthor.equals(tweet.getAuthorId());
  }

  Function<Stream<Tweet>, Stream<Tweet>> applyRange(int count, int offset) {
    return (Stream<Tweet> stream) -> stream.skip(count).limit(offset);
  }

  public void postTweet(Tweet tweet) {
    Stream<Tweet> firstOnTop = Stream.of(tweet);
    tweets = () -> tweetsSource().get().map(items -> Stream.concat(firstOnTop, items));
  }
}