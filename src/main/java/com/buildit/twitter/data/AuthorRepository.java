package com.buildit.twitter.data;

import java.util.Optional;
import java.util.stream.Stream;

import com.buildit.twitter.data.dto.Author;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthorRepository implements IAuthorRepository {
  private Optional<Stream<Author>> authors;

  public Optional<Stream<Author>> getAuthors() {
    return authors;
  }
}