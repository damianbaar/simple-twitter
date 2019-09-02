package com.buildit.twitter;

import java.util.function.Function;

import com.buildit.twitter.data.AuthorRepository;
import com.buildit.twitter.data.FollowersEdgeRepository;
import com.buildit.twitter.data.IAuthorRepository;
import com.buildit.twitter.data.IFollowerEdgesRepository;
import com.buildit.twitter.data.ITweetRepository;
import com.buildit.twitter.data.TweetRepository;
import com.buildit.twitter.data.dto.Author;
import com.buildit.twitter.data.dto.FollowerEdge;
import com.buildit.twitter.data.dto.Tweet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.vavr.Function2;
import io.vavr.collection.List;
import io.vavr.control.Option;

@Configuration
public class MockDataConfiguration {

  @Bean
  public IAuthorRepository author() {
    List<String> names = List.of("Thor", "Loki", "Hulk");
    List<Author> authors = names.map(name -> Author.builder().name(name).build());
    return AuthorRepository.builder().authors(Option.of(authors)).build();
  }

  @Bean
  public ITweetRepository tweet(IAuthorRepository authors) {
    Function<Integer, Tweet> makeTweet = (Integer id) -> {
      Author firstAuthor = authors.getAuthors().get().get(0);
      return Tweet.builder().message("some message" + id).authorId(firstAuthor.getId()).build();
    };
    List<Tweet> tweets = List.range(0, 10).map(makeTweet);
    return TweetRepository.builder().tweets(Option.of(tweets)).build();
  }

  @Bean
  public IFollowerEdgesRepository followers(IAuthorRepository authors) {
    Function2<Author, Author, FollowerEdge> makeEdge = (userId, followUserId) -> {
      return FollowerEdge.builder().followUserId(followUserId.getId()).userId(userId.getId()).build();
    };

    List<Author> authorsIndex = authors.getAuthors().getOrElse(List.of(Author.builder().name("UNKNOWN").build()));
    Author author1 = authorsIndex.get(0);
    Author author2 = authorsIndex.get(1);
    Author author3 = authorsIndex.get(2);

    return FollowersEdgeRepository.builder().edges(Option.of(List.of(
      makeEdge.apply(author1, author2),
      makeEdge.apply(author2, author1), 
      makeEdge.apply(author3, author1))
    )).build();
  }
}