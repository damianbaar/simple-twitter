package com.buildit.twitter.data.dto;

import java.util.UUID;

import lombok.Value;

@Value
public class Author {
  private String id;
  private String name;

  @Override
  public String toString() {
    return String.format("%s (id: %d, author: %d)", name, id);
  }

  static public Author make(String name) {
    String id = UUID.randomUUID().toString();
    return new Author(id, name);
  }
}