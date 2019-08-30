package com.buildit.twitter.graphql.resolver.twitter;

import java.text.MessageFormat;

import com.buildit.twitter.data.ITweetRepository;
import com.buildit.twitter.data.TweetValidation;
import com.buildit.twitter.data.dto.Tweet;
import com.buildit.twitter.graphql.error.ValidationError;
import com.buildit.twitter.graphql.error.ValidationFailed;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.GraphQLException;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import lombok.var;

@Component("TwitterMutationResolver")
public class Mutation implements GraphQLMutationResolver {
  @Autowired
  private ITweetRepository tweetRepository;

  @Autowired
  private TweetValidation tweetValidation;

  public Tweet addTweet(String message, String authorId) throws GraphQLException {
    Validation<Seq<String>, Tweet> maybeTweet = tweetValidation.validate(message, authorId);

    if (maybeTweet.isValid()) {
      maybeTweet.forEach(tweet -> tweetRepository.addTweet(tweet));
      return maybeTweet.get();
    } else {
      var messages = maybeTweet.getError().intersperse(", ").fold("", String::concat);
      throw new ValidationError(MessageFormat.format("Tweet cannot be saved, {0}", messages));
    }
  }
}