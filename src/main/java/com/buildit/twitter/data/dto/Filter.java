package com.buildit.twitter.data.dto;

import java.util.function.Predicate;

public class Filter {
  static public Predicate<Tweet> matchTweetWithAuthorById(String wantedAuthor) {
    return tweet -> wantedAuthor.equals(tweet.getAuthorId());
  }
}