package com.buildit.twitter.graphql.resolver.twitter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.buildit.twitter.data.ITweetRepository;
import com.buildit.twitter.data.dto.Tweet;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("TwitterQueryResolver")
public class Query implements GraphQLQueryResolver {
  @Autowired
  private ITweetRepository tweetRepository;

  public List<Tweet> tweets(int count, int offset) {
    if (log.isInfoEnabled())
      log.info("Getting tweets, count: {}, offset: {}", count, offset);

    return tweetRepository.getTweets(count, offset).orElse(Stream.<Tweet>of()).collect(Collectors.toList());
  }

  public List<Tweet> tweetsByAuthor(int count, int offset, String authorId) {
    if (log.isInfoEnabled())
      log.info("Getting tweetsByAuthor, author: {}, count: {}, offset: {}", authorId, count, offset);

    return tweetRepository.getTweetsByAuthor(authorId, count, offset).orElse(Stream.<Tweet>of())
        .collect(Collectors.toList());
  };
}
