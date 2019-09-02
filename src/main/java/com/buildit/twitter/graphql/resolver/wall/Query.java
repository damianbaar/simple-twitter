package com.buildit.twitter.graphql.resolver.wall;

import com.buildit.twitter.data.WallView;
import com.buildit.twitter.data.dto.Tweet;
import com.buildit.twitter.graphql.resolver.wall.UserWallInput;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.vavr.collection.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("TweeterWallResolver")
public class Query implements GraphQLQueryResolver {

  @Autowired
  private WallView wallView;

  public List<Tweet> userWall(UserWallInput input) {
    if (log.isInfoEnabled())
      log.info("Getting author wall, authorId: {}", input.getAuthorId());

    return wallView.getUserWall(input).collect(List.collector());
  }
}
