package com.buildit.twitter.graphql.resolver.twitter;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;

import java.text.MessageFormat;

import com.buildit.twitter.data.ITweetRepository;
import com.buildit.twitter.data.TweetValidation;
import com.buildit.twitter.data.dto.Tweet;
import com.buildit.twitter.graphql.error.ValidationError;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("TwitterMutationResolver")
public class Mutation implements GraphQLMutationResolver {

  @Autowired
  private ITweetRepository tweetRepository;

  @Autowired
  private TweetValidation tweetValidation;

  // INFO: with $Valid / $Invalid patterns loosing type interference
  /* @formatter:off */
  public Tweet addTweet(String message, String authorId) throws ValidationError {
    return Match(tweetValidation.validate(message, authorId)).of(
      Case($(m -> m.isValid()), m -> tweetRepository.addTweet(m.get())), 
      Case($(m -> m.isInvalid()), 
        e -> {
          String messages = e.getError().intersperse(", ").fold("", String::concat);
          throw new ValidationError(MessageFormat.format("Tweet cannot be saved, {0}", messages));
        })
    );
  }
  /* @formatter:on */
}