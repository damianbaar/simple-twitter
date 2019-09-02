package com.buildit.twitter.data;

import java.util.Optional;

import com.buildit.twitter.data.dto.Author;
import com.buildit.twitter.data.dto.FollowerEdge;

import io.vavr.collection.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FollowersEdgeRepository implements IFollowerEdgesRepository {
  @Builder.Default
  private Optional<List<FollowerEdge>> edges = Optional.of(List.empty());

  public Optional<List<FollowerEdge>> getEdges() {
    return edges;
  }

  public FollowerEdge follow(Author a, Author b) {
    return null;
  }
}
