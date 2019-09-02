package com.buildit.twitter.graphql.resolver.wall;

import com.buildit.twitter.data.WallView;
import com.buildit.twitter.data.dto.Tweet;
import com.buildit.twitter.graphql.resolver.wall.input.UserWallInput;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.vavr.collection.List;

@Component("TweeterWallResolver")
public class Query implements GraphQLQueryResolver {

  @Autowired
  private WallView wallView;

  public List<Tweet> userWall(UserWallInput input) {
    return wallView.getUserWall(input);
  }
}
