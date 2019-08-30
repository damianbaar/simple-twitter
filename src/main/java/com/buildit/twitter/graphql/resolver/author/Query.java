package com.buildit.twitter.graphql.resolver.author;

import com.buildit.twitter.data.IAuthorRepository;
import com.buildit.twitter.data.dto.Author;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("AuthorQueryResolver")
public class Query implements GraphQLQueryResolver {

  @Autowired
  private IAuthorRepository authorRepository;

  public Author[] authors() {
    if (log.isInfoEnabled())
      log.info("Getting authors");

    return (Author[]) authorRepository.getAuthors().toArray();
  }
}