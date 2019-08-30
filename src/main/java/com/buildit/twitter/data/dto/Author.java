package com.buildit.twitter.data.dto;

import java.util.UUID;

import io.vavr.collection.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Author {
  @Builder.Default
  private String id = UUID.randomUUID().toString();

  @Builder.Default
  private List<Tweet> tweets = List.empty();

  @Builder.Default
  private List<FollowerEdge> followers = List.empty();

  private String name;
}