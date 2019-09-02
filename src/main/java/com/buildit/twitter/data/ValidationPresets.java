package com.buildit.twitter.data;

import java.text.MessageFormat;

import io.vavr.API.Match.Case;
import io.vavr.control.Option;
import io.vavr.control.Validation;

import static io.vavr.API.$;
import static io.vavr.API.Case;

public class ValidationPresets {

  public static <T> Case<Option<T>, Validation<String, T>> THERE_IS_NO_SUCH_ENTITY(String entityName) {
    return Case($(a -> a.isEmpty()), Validation.invalid(MessageFormat.format("There is no such, {0}", entityName)));
  }
  public static <T> Case<Option<T>, Validation<String, String>> KEY_ALREADY_DEFINED(String msg) {
    return Case($(a -> a.isDefined()), Validation.invalid(MessageFormat.format("There is already such {0}", msg)));
  }
}