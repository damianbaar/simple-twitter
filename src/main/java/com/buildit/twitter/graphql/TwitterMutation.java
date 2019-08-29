package com.buildit.twitter.graphql;

import java.util.UUID;

import com.buildit.twitter.dao.TweetDao;
import com.buildit.twitter.dto.Author;
import com.buildit.twitter.dto.Tweet;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TwitterMutation implements GraphQLMutationResolver {
  private TweetDao tweetDao;

  public Tweet addTweet(String message, Author author) {
    Tweet tweet = new Tweet();
    tweet.setId(UUID.randomUUID().toString());
    tweet.setAuthor(author);

    tweetDao.saveTweet(tweet);
    return tweet;
  }
}