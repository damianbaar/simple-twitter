package com.buildit.twitter.graphql;

import java.util.List;

import com.buildit.twitter.dao.TweetDao;
import com.buildit.twitter.dto.Tweet;
import com.coxautodev.graphql.tools.GraphQLResolver;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class TweetResolver implements GraphQLResolver<Tweet> {
  private TweetDao tweetDao;

  public List<Tweet> getTweetsByAuthor(String authorId, int count, int offset) {
    return tweetDao.getTweetsByAuthor(authorId, count, offset);
  }
}