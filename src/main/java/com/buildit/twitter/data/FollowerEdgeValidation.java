package com.buildit.twitter.data;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;

import com.buildit.twitter.data.dto.Author;
import com.buildit.twitter.data.dto.FollowerEdge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Option;
import io.vavr.control.Validation;

@Component
public class FollowerEdgeValidation {
  @Autowired
  private IAuthorRepository authorRepository;

  @Autowired
  private IFollowerEdgesRepository followRepository;

  public Validation<Seq<String>, FollowerEdge> checkIfUsersExists(String userId, String followUserId) {
    return Validation
      .combine(
        checkIfSuchRelationExists(userId, followUserId),
        checkIfUserExists(userId),
        checkIfUserExists(followUserId)
      ).ap((alreadyExists, user, followed) -> 
        FollowerEdge.builder().userId(userId).followUserId(followUserId).build());
  }

  Validation<String, String> checkIfSuchRelationExists(String userId, String followUser) {
    List<FollowerEdge> edges = followRepository.getEdges().get();

    Option<FollowerEdge> hasRelation = edges.find(edge ->
      edge.getUserId().equals(userId) && edge.getFollowUserId().equals(followUser)
    );

    return Match(hasRelation).of(
      ValidationPresets.<FollowerEdge>KEY_ALREADY_DEFINED("relation"),
      Case($(), empty -> Validation.valid("")));
  };

  Validation<String, Author> checkIfUserExists(String userId) {
    return Match(authorRepository.getAuthorById(userId)).of(
      ValidationPresets.<Author>THERE_IS_NO_SUCH_ENTITY("author"),
      Case($(), author -> Validation.valid(author.get())));
  };
}