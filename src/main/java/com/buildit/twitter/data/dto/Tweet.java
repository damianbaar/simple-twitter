package com.buildit.twitter.data.dto;

import java.util.UUID;

import lombok.Value;

@Value
public class Tweet {
  private String id;
  private String message;
  private String authorId;

  static public Tweet make(String message, String authorId) {
    String id = UUID.randomUUID().toString();
    return new Tweet(id, message, authorId);
  }
}