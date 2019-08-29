package com.buildit.twitter.dao;

import java.util.List;
import java.util.stream.Stream;

import com.buildit.twitter.dto.Author;

public class AuthorDao {
  private List<Author> authors;

  public AuthorDao(List<Author> authors) {
    this.authors = authors;
  }

  public Stream<Author> getAuthors() {
    return authors.stream();
  }
}