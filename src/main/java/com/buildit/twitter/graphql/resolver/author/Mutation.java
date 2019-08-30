package com.buildit.twitter.graphql.resolver.author;

import java.text.MessageFormat;

import com.buildit.twitter.data.AuthorValidator;
import com.buildit.twitter.data.IAuthorRepository;
import com.buildit.twitter.data.dto.Author;
import com.buildit.twitter.graphql.error.ValidationError;
import com.buildit.twitter.graphql.error.ValidationFailed;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static io.vavr.API.Match;
import static io.vavr.API.$;
import static io.vavr.API.Case;
import io.vavr.control.Validation;

@Component("AuthorMutationResolver")
public class Mutation implements GraphQLMutationResolver {

  @Autowired
  private AuthorValidator authorValidator;

  @Autowired
  private IAuthorRepository authorRepository;

  /* @formatter:off */
  public Author addAuthor(String name) throws ValidationError {
    return Match(authorValidator.validate(name)).of(
      Case($(m -> m.isValid()), m -> authorRepository.addAuthor(m.get())), 
      Case($(m -> m.isInvalid()), 
        e -> {
          throw new ValidationError(MessageFormat.format("Tweet cannot be saved, {0}", e.getError()));
        })
    );
  }
  /* @formatter:on */
}