package com.buildit.twitter.data;

import com.buildit.twitter.data.dto.FollowerEdge;

import io.vavr.collection.List;
import io.vavr.control.Option;

public interface IFollowerEdgesRepository {
  FollowerEdge addEdge(FollowerEdge edge);

  Option<List<FollowerEdge>> getEdges();

  Boolean isFollowing(String userId, String userFollowId);
  List<FollowerEdge> getFollowers(String userId);
}