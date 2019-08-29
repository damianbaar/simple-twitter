package com.buildit.twitter.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.buildit.twitter.dao.AuthorDao;
import com.buildit.twitter.dao.TweetDao;
import com.buildit.twitter.dto.Author;
import com.buildit.twitter.dto.Tweet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class GraphQLData {
  @Bean
  public AuthorDao authorDao() {
    String[] names = { "Thor", "Loki", "Hulk" };
    Function<String, Author> makeAuthor = (String name) -> {
      return Author.make(name);
    };
    Stream<Author> authors = new ArrayList<>(Arrays.asList(names)).stream().map(makeAuthor);
    return new AuthorDao(authors.collect(Collectors.toList()));
  }

  @Bean
  public TweetDao tweetDao(AuthorDao authors) {
    IntFunction<Tweet> makeTweet = (int id) -> {
      return Tweet.make("message" + id, authors.getAuthors().findFirst().get());
    };
    Stream<Tweet> tweets = IntStream.range(0, 10).mapToObj(makeTweet);
    return new TweetDao(tweets.collect(Collectors.toList()));
  }
}