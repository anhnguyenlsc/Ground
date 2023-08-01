package com.ground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class GroundApplication {

  public static void main(String[] args) {
    SpringApplication.run(GroundApplication.class, args);
  }

  @GetMapping("/websession")
  public Mono<String> getSession(WebSession session) {
    session.start();

    session.getAttributes().putIfAbsent("note", "Howdy Cosmic Spheroid!");
    return Mono.just((String) session.getAttributes().get("note"));
  }

}
