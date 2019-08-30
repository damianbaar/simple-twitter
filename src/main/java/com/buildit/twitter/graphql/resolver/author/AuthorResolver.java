package com.buildit.twitter.graphql.resolver.author;

import com.buildit.twitter.data.ITweetRepository;
import com.buildit.twitter.data.dto.Author;
import com.buildit.twitter.data.dto.Filter;
import com.buildit.twitter.data.dto.Tweet;
import com.coxautodev.graphql.tools.GraphQLResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.vavr.collection.List;

@Component
public class AuthorResolver implements GraphQLResolver<Author> {

  @Autowired
  private ITweetRepository tweetRepository;

  public List<Tweet> tweets(Author author) {
    return tweetRepository.getTweets().filter(Filter.matchTweetWithAuthorById(author.getId()))
        .collect(List.collector());
  }
}