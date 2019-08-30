package com.buildit.twitter.data;

import java.util.Optional;
import java.util.stream.Stream;

import com.buildit.twitter.data.dto.Tweet;

import io.vavr.collection.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TweetRepository implements ITweetRepository {
  @Builder.Default
  private Optional<List<Tweet>> tweets = Optional.of(List.empty());

  public Stream<Tweet> getTweets() {
    return tweets.get().toJavaStream();
  }

  public Tweet addTweet(Tweet tweet) {
    tweets = Optional.of(tweets.get().append(tweet));
    return tweet;
  }
}