package com.buildit.twitter.dto;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

public class Author {
  @Getter
  @Setter
  private String id;

  @Getter
  @Setter
  private String name;

  @Getter
  @Setter
  private List<Tweet> tweets;

  @Getter
  @Setter
  private List<FollowerEdge> followers;

  @Override
  public String toString() {
    return String.format("%s (id: %d, author: %d)", name, id);
  }

  static public Author make(String name) {
    Author tweet = new Author();
    tweet.id = UUID.randomUUID().toString();
    tweet.name = name;
    return tweet;
  }
}