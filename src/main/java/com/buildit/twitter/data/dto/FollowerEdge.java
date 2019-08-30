package com.buildit.twitter.data.dto;

import java.util.UUID;

import lombok.Value;

@Value
public class FollowerEdge {
  private String id;
  private Author follower;
  private Author following;

  static public FollowerEdge make(Author follower, Author following) {
    String id = UUID.randomUUID().toString();
    return new FollowerEdge(id, follower, following);
  }
}