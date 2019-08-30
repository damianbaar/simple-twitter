package com.buildit.twitter.data;

import java.util.stream.Stream;

import com.buildit.twitter.data.dto.Author;

public interface IAuthorRepository extends IRepository<Author> {
  Stream<Author> getAuthors();
}