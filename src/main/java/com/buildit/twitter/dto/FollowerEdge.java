package com.buildit.twitter.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

public class FollowerEdge {
  @Getter
  @Setter
  private String id;

  @Getter
  @Setter
  private Author follower;

  @Getter
  @Setter
  private Author following;

  @Override
  public String toString() {
    return String.format("%s (id: %d, author: %d)", id);
  }

  static public FollowerEdge make(Author follower, Author following) {
    FollowerEdge edge = new FollowerEdge();
    edge.id = UUID.randomUUID().toString();
    edge.follower = follower;
    edge.following = following;
    return edge;
  }
}