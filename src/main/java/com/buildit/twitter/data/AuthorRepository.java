package com.buildit.twitter.data;

import java.util.Optional;

import com.buildit.twitter.data.dto.Author;

import io.vavr.collection.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorRepository implements IAuthorRepository {
  @Builder.Default
  private Optional<List<Author>> authors = Optional.of(List.empty());

  public Optional<List<Author>> getAuthors() {
    return authors;
  }

  public Author addAuthor(Author author) {
    authors = Optional.of(authors.get().append(author));
    return author;
  }
}
