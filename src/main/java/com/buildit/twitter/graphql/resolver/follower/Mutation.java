package com.buildit.twitter.graphql.resolver.follower;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;

import java.text.MessageFormat;

import com.buildit.twitter.data.FollowerEdgeValidation;
import com.buildit.twitter.data.IFollowerEdgesRepository;
import com.buildit.twitter.data.dto.FollowerEdge;
import com.buildit.twitter.graphql.error.ValidationError;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("FollowerMutationResolver")
public class Mutation implements GraphQLMutationResolver {
  
  @Autowired
  private FollowerEdgeValidation edgeValidation;

  @Autowired
  private IFollowerEdgesRepository followRepository;

  public FollowerEdge addFollower(String userId, String followUserId) throws ValidationError {
    return Match(edgeValidation.checkIfUsersExists(userId, followUserId)).of(
      Case($(m -> m.isValid()), m -> followRepository.addEdge(m.get())), 
      Case($(m -> m.isInvalid()), 
        e -> {
          throw new ValidationError(MessageFormat.format("Edge cannot be saved, {0}", e.getError()));
        })
    );
  }
}