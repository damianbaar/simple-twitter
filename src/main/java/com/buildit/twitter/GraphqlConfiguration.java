package com.buildit.twitter;

import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class GraphQLConfiguration {

  @Bean
  public TweetDao tweetDao() {
    IntFunction<Tweet> makeTweet = (int id) -> {
      return Tweet.make("message" + id, Author.make("damian"));
    };
    Stream<Tweet> tweets = IntStream.range(0, 10).mapToObj(makeTweet);

    log.trace("dasdsaad", "%s", tweets);

    return new TweetDao(tweets.collect(Collectors.toList()));
  }

  @Bean
  public TweetResolver tweetResolver(TweetDao tweetDao) {
    return new TweetResolver(tweetDao);
  }

  @Bean
  public TwitterQuery query(TweetDao dao) {
    return new TwitterQuery(dao);
  }

  @Bean
  public TwitterMutation mutation(TweetDao dao) {
    return new TwitterMutation(dao);
  }
}