package com.buildit.twitter.graphql;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;

@Configuration
public class Scalars {

  @Bean
  public GraphQLScalarType jsonType() {
      return ExtendedScalars.DateTime;
  }
}
