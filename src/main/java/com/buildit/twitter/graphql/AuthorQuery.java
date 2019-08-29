package com.buildit.twitter.graphql;

import com.buildit.twitter.dao.AuthorDao;
import com.buildit.twitter.dto.Author;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class AuthorQuery implements GraphQLQueryResolver {
  private AuthorDao authorDao;

  public Author[] authors() {
    if (log.isInfoEnabled())
      log.info("Getting authors");

    return (Author[]) authorDao.getAuthors().toArray();
  }
}