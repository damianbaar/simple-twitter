package com.buildit.twitter.graphql.error;

import java.util.List;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

// check ValidationError from graphql
public class ValidationError extends RuntimeException implements GraphQLError {
  private static final long serialVersionUID = 1L;
  private String message;

  public ValidationError(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public List<SourceLocation> getLocations() {
    return null;
  }

  @Override
  public ErrorClassification getErrorType() {
    return null;
  }
}
