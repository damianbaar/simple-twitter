package com.buildit.twitter.data;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.buildit.twitter.data.dto.Author;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorRepository implements IAuthorRepository {
  private Supplier<Optional<Stream<Author>>> authors;

  @Autowired
  private Helpers helpers;

  public Supplier<Optional<Stream<Author>>> source() {
    return () -> Optional.of(authors.get().orElse(Stream.<Author>of()));
  }

  public Stream<Author> getAuthors() {
    return source().get().orElse(Stream.<Author>of());
  }

  public Supplier<Optional<Stream<Author>>> add(Author author) {
    authors = helpers.add(author, source());
    return authors;
  }
}
