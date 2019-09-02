package com.buildit.twitter.graphql.resolver.author;

import com.buildit.twitter.data.IAuthorRepository;
import com.buildit.twitter.data.IFollowerEdgesRepository;
import com.buildit.twitter.data.ITweetRepository;
import com.buildit.twitter.data.dto.Author;
import com.buildit.twitter.data.dto.Filter;
import com.buildit.twitter.data.dto.FollowerEdge;
import com.buildit.twitter.data.dto.Tweet;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.google.common.base.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.vavr.Function1;
import io.vavr.collection.List;

@Component
// FIXME use memoize
public class AuthorResolver implements GraphQLResolver<Author> {

  @Autowired
  private ITweetRepository tweetRepository;

  @Autowired
  private IAuthorRepository authorRepository;

  @Autowired
  private IFollowerEdgesRepository followersRepository;

  public List<Tweet> tweets(Author author) {
    return tweetRepository
        .getTweets()
        .filter(i -> Filter.matchTweetWithAuthorById(author.getId()).apply(i))
        .collect(List.collector());
  }

  public List<Author> followers(Author owner) {
    List<Author> authors = authorRepository.getAuthors().get();

    Function1<FollowerEdge, Author> mapEdgeToUser = edge ->
      authors.find(author -> author.getId().equals(edge.getFollowUserId())).get();

    Predicate<FollowerEdge> getEdgesForUser = user -> user.getUserId().equals(owner.getId());

    return followersRepository
        .getEdges()
        .map(e -> e.filter(getEdgesForUser).map(mapEdgeToUser))
        .get()
        .collect(List.collector());
  }
}