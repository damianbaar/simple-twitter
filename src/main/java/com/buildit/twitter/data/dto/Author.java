package com.buildit.twitter.data.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Author {
  private String id = UUID.randomUUID().toString();
  private String name;
  private Tweet[] tweets;
  private FollowerEdge[] followers;

  @Override
  public String toString() {
    return String.format("%s (id: %d, author: %d)", name, id);
  }
}