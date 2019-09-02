package com.buildit.twitter.graphql.resolver.wall;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserWallInput {
  private String authorId;
  private Boolean withFollowersTweets;
  private Integer count;
  private Integer offset;
}