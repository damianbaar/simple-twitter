package com.buildit.twitter.data.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Tweet {
  private String id = UUID.randomUUID().toString();
  private String message;
  private String authorId;
}