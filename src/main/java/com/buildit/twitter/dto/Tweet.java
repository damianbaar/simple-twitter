package com.buildit.twitter.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

public class Tweet {
  @Getter
  @Setter
  private String id;

  @Getter
  @Setter
  private String message;

  @Getter
  @Setter
  private Author author;

  @Override
  public String toString() {
    return String.format("%s (id: %d, author: %d)", message, author, id);
  }

  static public Tweet make(String message, Author author) {
    Tweet tweet = new Tweet();
    tweet.id = UUID.randomUUID().toString();
    tweet.author = author;
    tweet.message = message;
    return tweet;
  }
}