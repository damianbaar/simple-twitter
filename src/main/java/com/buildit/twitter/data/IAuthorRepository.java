package com.buildit.twitter.data;

import java.util.stream.Stream;

import com.buildit.twitter.data.dto.Author;

public interface IAuthorRepository {
  Stream<Author> getAuthors();
}