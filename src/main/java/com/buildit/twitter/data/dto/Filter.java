package com.buildit.twitter.data.dto;

import java.util.function.Predicate;

public class Filter {
  static public Predicate<Tweet> matchAuthorById(String wantedAuthor) {
    return tweet -> wantedAuthor.equals(tweet.getAuthorId());
  }
}