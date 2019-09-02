package com.buildit.twitter.data;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;

import com.buildit.twitter.data.dto.Author;

import org.springframework.stereotype.Component;

import io.vavr.control.Validation;

@Component
public class AuthorValidator {

  public Validation<String, Author> validate(String name) {
    return validateName(name)
            .map(valid -> Author.builder().name(valid).build());
  }

  private Validation<String, String> validateName(String field) {
    return Match(field).of(
      Case($(a -> a.isEmpty()), Validation.invalid("Missing name")),
      Case($(), Validation.valid(field)));
  }
}