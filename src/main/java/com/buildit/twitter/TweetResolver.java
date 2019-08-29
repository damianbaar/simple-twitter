package com.buildit.twitter;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLResolver;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class TweetResolver implements GraphQLResolver<Tweet> {
  private TweetDao tweetDao;

  public List<Tweet> getTweetsByAuthor(String authorId) {
    return tweetDao.getTweetsByAuthor(authorId);
  }
}