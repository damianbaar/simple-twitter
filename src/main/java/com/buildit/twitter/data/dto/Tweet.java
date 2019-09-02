package com.buildit.twitter.data.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Tweet {
  @Builder.Default
  private String id = UUID.randomUUID().toString();

  @Builder.Default
  private OffsetDateTime creationDate = OffsetDateTime.now();

  private String message;
  private String authorId;
}