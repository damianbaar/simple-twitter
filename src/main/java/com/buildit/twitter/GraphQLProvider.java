package com.buildit.twitter;

import java.io.IOException;
import java.net.URL;

import javax.annotation.PostConstruct;

import com.coxautodev.graphql.tools.SchemaParser;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;

@Component
public class GraphQLProvider {

  private GraphQL graphQL;
  private TwitterQuery query;
  private TwitterMutation mutation;

  @Bean
  public GraphQL graphQL() {
    return graphQL;
  }

  @Autowired
  public GraphQLProvider(TwitterQuery query, TwitterMutation mutation) {
    this.query = query;
    this.mutation = mutation;
  }

  @PostConstruct
  public void init() throws IOException {
    URL url = Resources.getResource("graphql/twitter.graphql");
    String sdl = Resources.toString(url, Charsets.UTF_8);
    GraphQLSchema graphQLSchema = buildSchema(sdl);
    this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
  }

  private GraphQLSchema buildSchema(String file) {
    return SchemaParser.newParser().schemaString(file).resolvers(query).resolvers(mutation).build()
        .makeExecutableSchema();
  }
}