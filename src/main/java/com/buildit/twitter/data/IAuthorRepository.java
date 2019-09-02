package com.buildit.twitter.data;

import com.buildit.twitter.data.dto.Author;

import io.vavr.collection.List;
import io.vavr.control.Option;

public interface IAuthorRepository {
  Option<List<Author>> getAuthors();

  Author addAuthor(Author author);

  Option<Author> getAuthorById(String authorId);
}