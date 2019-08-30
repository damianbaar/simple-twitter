package com.buildit.twitter.graphql.resolver.author;

import com.buildit.twitter.data.IAuthorRepository;
import com.buildit.twitter.data.dto.Author;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("AuthorMutationResolver")
public class Mutation implements GraphQLMutationResolver {

  @Autowired
  private IAuthorRepository authorRepository;

  public Author addAuthor(String name) {
    Author author = Author.builder().name(name).build();
    authorRepository.addAuthor(author);
    return author;
  }
}