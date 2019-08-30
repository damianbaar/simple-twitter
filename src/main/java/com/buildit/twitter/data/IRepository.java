package com.buildit.twitter.data;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface IRepository<T> {
  Supplier<Optional<Stream<T>>> source();

  Supplier<Optional<Stream<T>>> add(T input);
}