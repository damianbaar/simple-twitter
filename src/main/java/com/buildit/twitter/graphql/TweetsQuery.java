package com.buildit.twitter.graphql;

import java.util.List;

import com.buildit.twitter.dao.TweetDao;
import com.buildit.twitter.dto.Tweet;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class TweetsQuery implements GraphQLQueryResolver {
  private TweetDao tweetDao;

  public List<Tweet> tweets(int count, int offset) {
    if (log.isInfoEnabled())
      log.info("Getting tweets, count: {}, offset: {}", count, offset);

    return tweetDao.getTweets(count, offset);
  }

  public List<Tweet> tweetsByAuthor(int count, int offset, String authorId) {
    if (log.isInfoEnabled())
      log.info("Getting tweetsByAuthor, author: {}, count: {}, offset: {}", authorId, count, offset);

    return tweetDao.getTweetsByAuthor(authorId, count, offset);
  };
}