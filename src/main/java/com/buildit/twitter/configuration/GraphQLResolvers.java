package com.buildit.twitter.configuration;

import com.buildit.twitter.dao.TweetDao;
import com.buildit.twitter.graphql.TweetResolver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class GraphQLResolvers {
  @Bean
  public TweetResolver tweetResolver(TweetDao tweetDao) {
    return new TweetResolver(tweetDao);
  }
}