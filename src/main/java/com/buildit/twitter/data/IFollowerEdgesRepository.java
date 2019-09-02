package com.buildit.twitter.data;

import java.util.Optional;

import com.buildit.twitter.data.dto.Author;
import com.buildit.twitter.data.dto.FollowerEdge;

import io.vavr.collection.List;

public interface IFollowerEdgesRepository {
  FollowerEdge follow(Author a, Author b);

  Optional<List<FollowerEdge>> getEdges();
}