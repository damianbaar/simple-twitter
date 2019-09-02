package com.buildit.twitter.graphql.resolver.wall.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWallInput {
  private String authorId;
  private Boolean withFollowersTweets;
  private Integer count;
  private Integer offset;
}