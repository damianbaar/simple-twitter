package com.buildit.twitter.data;

import com.buildit.twitter.data.dto.Tweet;

import org.springframework.stereotype.Component;

import io.vavr.collection.Seq;
import io.vavr.control.Validation;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;

import java.text.MessageFormat;

@Component
public class TweetValidation {
  private static final Integer MAX_MESSAGE_LENGTH = 140;

  public Validation<Seq<String>, Tweet> validate(String message, String authorId) {
    return Validation
      .combine(
        validateMessage(message), 
        validateAuthor(authorId)
      ).ap((msg, author) -> {
        return Tweet.builder().message(msg).authorId(author).build();
      });
  }

  Validation<String, String> validateMessage(String field) {
    return Match(field).of(
      Case($(m -> m.isEmpty()), Validation.invalid("Empty message")),
      Case($(m -> m.length() > MAX_MESSAGE_LENGTH),
        Validation.invalid(MessageFormat.format("Message is too long, should not exceed {0}", MAX_MESSAGE_LENGTH))),
      Case($(m -> m.length() < 3), Validation.invalid("Message is too short.")), 
      Case($(), Validation.valid(field)));
  }

  private Validation<String, String> validateAuthor(String field) {
    return Match(field).of(
      Case($(a -> a.isEmpty()), Validation.invalid("Missing author")),
      Case($(), Validation.valid(field)));
  }
}