package com.buildit.twitter.data;

import java.util.stream.Stream;

import com.buildit.twitter.data.IAuthorRepository;
import com.buildit.twitter.data.IFollowerEdgesRepository;
import com.buildit.twitter.data.ITweetRepository;
import com.buildit.twitter.data.dto.Author;
import com.buildit.twitter.data.dto.Tweet;
import com.buildit.twitter.graphql.resolver.wall.UserWallInput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.vavr.collection.List;
import io.vavr.control.Option;
import lombok.var;

@Component
public class WallView {
  @Autowired
  private ITweetRepository tweetRepository;

  @Autowired
  private IAuthorRepository authorRepository;

  @Autowired
  private IFollowerEdgesRepository followEdgeRepository;

  public Stream<Tweet> getUserWall(UserWallInput input) {
    Option<Author> maybeAuthor = 
      authorRepository.getAuthorById(input.getAuthorId());

    var tweetAuthors = 
      input.getWithFollowersTweets()
        ? maybeAuthor.map(author -> 
              followEdgeRepository
                .getFollowers(author.getId())
                .map(edge -> edge.getFollowUserId())
                .append(author.getId()))
        : maybeAuthor.map(author -> List.of(author.getId()));
    
    // INFO: a bit rubbish since there is no sequence in vavr to do a transformation like
    // List<Optional<Type>>> -> Optional<List<Type>> - lambda lib handle it well
    return tweetAuthors
      .map(authors -> authors.map(author -> tweetRepository.getTweetsById(author))) // here we can apply also some limits
      .map(tweets -> tweets.reduce((a, b) -> Stream.concat(a, b)))
      .map(tweets -> tweets.skip(input.getOffset()).limit(input.getCount()))
      .map(tweets -> tweets.sorted((a, b) -> a.getCreationDate().isBefore(b.getCreationDate()) ? 1 : -1))
      .getOrElse(Stream.of());
  }
}
