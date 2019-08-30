package com.buildit.twitter.data.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FollowerEdge {
  @Builder.Default
  private String id = UUID.randomUUID().toString();
  private Author follower;
  private Author following;
}