
package com.buildit.twitter.configuration;

import com.buildit.twitter.dao.AuthorDao;
import com.buildit.twitter.dao.TweetDao;
import com.buildit.twitter.graphql.AuthorQuery;
import com.buildit.twitter.graphql.TweetsQuery;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class GraphQLQueries {
  @Bean
  public TweetsQuery connectTweetsResolverToData(TweetDao dao) {
    return new TweetsQuery(dao);
  }

  @Bean
  public AuthorQuery connectAuthorResolverToData(AuthorDao dao) {
    return new AuthorQuery(dao);
  }
}