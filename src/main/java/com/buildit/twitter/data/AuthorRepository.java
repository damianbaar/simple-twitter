package com.buildit.twitter.data;

import com.buildit.twitter.data.dto.Author;

import io.vavr.collection.List;
import io.vavr.control.Option;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorRepository implements IAuthorRepository {
  @Builder.Default
  private Option<List<Author>> authors = Option.of(List.empty());

  public Option<List<Author>> getAuthors() {
    return authors;
  }

  public Author addAuthor(Author author) {
    authors = Option.of(authors.get().append(author));
    return author;
  }

  public Option<Author> getAuthorById(String authorId) {
    return authors.get().find(user -> user.getId().equals(authorId));
  }
}
