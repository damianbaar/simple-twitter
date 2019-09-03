package com.buildit.twitter;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import com.buildit.twitter.data.IAuthorRepository;
import com.buildit.twitter.data.dto.Author;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// FIXME add given / when / then and some mock data
// FIXME provide beans per case rather than rely on MockData
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationIngrationTests {

  @Autowired
  private GraphQLTestTemplate graphQLTestTemplate;

  @Autowired
  private IAuthorRepository authorRepository;

  @Test
  public void getAuthorQuery() throws IOException {
    GraphQLResponse response = graphQLTestTemplate.perform("queries/GetAuthors.graphql", null);

    JsonNode result = response.readTree();
    int authors = result.path("data").path("authors").size();

    assertTrue(response.isOk());
    assertTrue(authors > 0);
  }

  @Test
  public void getTweetsQuery() throws IOException {
    GraphQLResponse response = graphQLTestTemplate.perform("queries/GetTweets.graphql", null);

    JsonNode result = response.readTree();
    int tweets = result.path("data").path("tweets").size();

    assertTrue(response.isOk());
    assertTrue(tweets > 0);
  }

  @Test
  public void getUserWallWhenIdIsWrong() throws IOException {
    GraphQLResponse response = graphQLTestTemplate.perform("queries/GetWall.graphql");
    JsonNode result = response.readTree();
    int errors = result.get("errors").size();

    assertTrue(response.isOk());
    assertTrue(errors > 0);
  }

  @Test
  public void getUserWallWhenAllOk() throws IOException {
    Author author = authorRepository.getAuthors().get().get(0);
    ObjectNode args = new ObjectMapper().createObjectNode();

    args
      .putObject("input")
      .put("authorId", author.getId())
      .put("count", 100)
      .put("offset", 0)
      .put("withFollowersTweets", true);

    GraphQLResponse response = graphQLTestTemplate.perform("queries/GetWall.graphql", args);
    JsonNode result = response.readTree();

    boolean noErrors = result.path("errors").isMissingNode();
    int dataSize = result.path("data").get("userWall").size();

    assertTrue(response.isOk());
    assertTrue(dataSize > 0);
    assertTrue(noErrors);
  }

}
