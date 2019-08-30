package com.buildit.twitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.IntFunction;
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
    String[] names = { "Thor", "Loki", "Hulk" };
    Stream<Author> authors = new ArrayList<>(Arrays.asList(names)).stream().map(Author::make);
    return new AuthorRepository(Optional.of(authors));
  }

  private Author UNKNOWN = Author.make("UNKNOWN");

  @Bean
  public ITweetRepository tweet(IAuthorRepository authors) {
    // INFO: I'm sure here there will be an author
    IntFunction<Tweet> makeTweet = (int id) -> {
      Optional<Author> firstAuthor = authors.getAuthors().map(stream -> stream.findFirst().orElse(UNKNOWN));
      return Tweet.make("some message" + id, firstAuthor.get().getId());
    };
    Stream<Tweet> tweets = IntStream.range(0, 10).mapToObj(makeTweet);
    return new TweetRepository(Optional.of(tweets));
  }
}