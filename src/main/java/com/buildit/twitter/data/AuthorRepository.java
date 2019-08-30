package com.buildit.twitter.data;

import java.util.stream.Stream;

import com.buildit.twitter.data.dto.Author;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthorRepository implements IAuthorRepository {
  private Stream<Author> authors;

  public Stream<Author> getAuthors() {
    return authors;
  }
}