package com.buildit.twitter;

import java.util.Optional;
import java.util.function.Function;

import com.buildit.twitter.data.AuthorRepository;
import com.buildit.twitter.data.IAuthorRepository;
import com.buildit.twitter.data.ITweetRepository;
import com.buildit.twitter.data.TweetRepository;
import com.buildit.twitter.data.dto.Author;
import com.buildit.twitter.data.dto.Tweet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.vavr.collection.List;

@Configuration
public class MockDataConfiguration {

  @Bean
  public IAuthorRepository author() {
    List<String> names = List.of("Thor", "Loki", "Hulk");
    List<Author> authors = names.map(name -> Author.builder().name(name).build());
    return AuthorRepository.builder().authors(Optional.of(authors)).build();
  }

  @Bean
  public ITweetRepository tweet(IAuthorRepository authors) {
    Function<Integer, Tweet> makeTweet = (Integer id) -> {
      Author firstAuthor = authors.getAuthors().get().get(0);
      return Tweet.builder().message("some message" + id).authorId(firstAuthor.getId()).build();
    };
    List<Tweet> tweets = List.range(0, 10).map(makeTweet);
    return TweetRepository.builder().tweets(Optional.of(tweets)).build();
  }
}