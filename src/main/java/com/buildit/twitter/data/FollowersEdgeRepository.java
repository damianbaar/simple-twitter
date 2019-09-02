package com.buildit.twitter.data;

import com.buildit.twitter.data.dto.FollowerEdge;

import io.vavr.collection.List;
import io.vavr.control.Option;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FollowersEdgeRepository implements IFollowerEdgesRepository {
  @Builder.Default
  private Option<List<FollowerEdge>> edges = Option.of(List.empty());

  public Option<List<FollowerEdge>> getEdges() {
    return edges;
  }

  public FollowerEdge addEdge(FollowerEdge edge) {
    edges  = Option.of(edges.get().append(edge));
    return edge;
  }

  public Boolean isFollowing(String userId, String userFollowId) {
    return edges.get().find(u -> u.getUserId().equals((userId))).isDefined();
  }

  public List<FollowerEdge> getFollowers(String userId) {
    return edges.get().filter(e -> e.getUserId().equals(userId));
  }
}
