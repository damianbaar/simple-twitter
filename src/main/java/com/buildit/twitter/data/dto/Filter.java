package com.buildit.twitter.data.dto;

import io.vavr.Function1;

public class Filter {
  static public Function1<Tweet, Boolean> matchTweetWithAuthorById(String wantedAuthor) {
    return tweet -> {
      return wantedAuthor.equals(tweet.getAuthorId());
    };
  }

}