package com.buildit.twitter.data;

import java.util.stream.Stream;

import com.buildit.twitter.data.dto.Tweet;

import io.vavr.collection.List;
import io.vavr.control.Option;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TweetRepository implements ITweetRepository {
  @Builder.Default
  private Option<List<Tweet>> tweets = Option.of(List.empty());

  public Stream<Tweet> getTweets() {
    return tweets.get().toJavaStream();
  }

  public Tweet addTweet(Tweet tweet) {
    tweets = Option.of(tweets.get().prepend(tweet));
    return tweet;
  }

  public Stream<Tweet> getTweetsById(String authorId) {
    return tweets.get().filter(tweet -> tweet.getAuthorId().equals(authorId)).toJavaStream();
  }
}