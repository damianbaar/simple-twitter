package com.buildit.twitter.data;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.buildit.twitter.data.dto.Author;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthorRepository implements IAuthorRepository {
  private Supplier<Optional<Stream<Author>>> authors;

  public Stream<Author> getAuthors() {
    return authors.get().orElse(Stream.<Author>of());
  }

  public void add(Author author) {
    Stream<Author> firstOnTop = Stream.of(author);
    authors = () -> authors.get().map(items -> Stream.concat(firstOnTop, items));
  }
}