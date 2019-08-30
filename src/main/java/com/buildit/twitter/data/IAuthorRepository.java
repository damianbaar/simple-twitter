package com.buildit.twitter.data;

import java.util.Optional;

import com.buildit.twitter.data.dto.Author;

import io.vavr.collection.List;

public interface IAuthorRepository {
  Optional<List<Author>> getAuthors();

  Author addAuthor(Author author);
}