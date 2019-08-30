package com.buildit.twitter.data;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

@Component
class Helpers {
  public <T> Supplier<Optional<Stream<T>>> add(T input, Supplier<Optional<Stream<T>>> source) {
    Stream<T> firstOnTop = Stream.of(input);
    return () -> source.get().map(items -> Stream.concat(firstOnTop, items));
  }
}