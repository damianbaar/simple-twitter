package com.buildit.twitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.buildit.twitter.data.AuthorRepository;
import com.buildit.twitter.data.IAuthorRepository;
import com.buildit.twitter.data.ITweetRepository;
import com.buildit.twitter.data.TweetRepository;
import com.buildit.twitter.data.dto.Author;
import com.buildit.twitter.data.dto.Tweet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MockDataConfiguration {

  @Bean
  public IAuthorRepository author() {
    ArrayList<String> names = new ArrayList<>(Arrays.asList("Thor", "Loki", "Hulk"));
    Supplier<Optional<Stream<Author>>> authors = () -> Optional
        .of(names.stream().map(name -> Author.builder().name(name).build()));
    return AuthorRepository.builder().authors(authors).build();
  }

  @Bean
  public ITweetRepository tweet(IAuthorRepository authors) {
    IntFunction<Tweet> makeTweet = (int id) -> {
      Author firstAuthor = authors.getAuthors().findFirst().get();
      return Tweet.builder().message("some message" + id).authorId(firstAuthor.getId()).build();
    };
    Supplier<Optional<Stream<Tweet>>> tweets = () -> Optional.of(IntStream.range(0, 10).mapToObj(makeTweet));
    return TweetRepository.builder().tweets(tweets).build();
  }
}