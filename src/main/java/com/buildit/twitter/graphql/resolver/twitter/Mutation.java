package com.buildit.twitter.graphql.resolver.twitter;

import com.buildit.twitter.data.ITweetRepository;
import com.buildit.twitter.data.dto.Author;
import com.buildit.twitter.data.dto.Tweet;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("TwitterMutationResolver")
public class Mutation implements GraphQLMutationResolver {

  @Autowired
  private ITweetRepository tweetRepository;

  public Tweet addTweet(String message, Author authorId) {
    Tweet tweet = Tweet.make(message, authorId.getId());
    tweetRepository.add(tweet);
    return tweet;
  }
}