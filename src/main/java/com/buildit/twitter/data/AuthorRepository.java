package com.buildit.twitter.data;

import java.util.List;
import java.util.stream.Stream;

import com.buildit.twitter.data.dto.Author;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthorRepository implements IAuthorRepository {
  private List<Author> authors;

  public Stream<Author> getAuthors() {
    return authors.stream();
  }
}