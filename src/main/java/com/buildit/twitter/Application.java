package com.buildit.twitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Application {

  public static void main(String[] args) {
    log.info("Hello hello");
    SpringApplication.run(Application.class, args);
  }
}