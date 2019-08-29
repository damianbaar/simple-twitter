package com.buildit.twitter;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class TwitterQuery implements GraphQLQueryResolver {
  private TweetDao tweetDao;

  public List<Tweet> tweets(int count, int offset) {
    log.trace("getting tweets");
    return tweetDao.getTweets(count, offset);
  }
}