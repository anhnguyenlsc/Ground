package com.ground.configuration.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class UserRouter {

  /*
  @Bean
  public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {

    return RouterFunctions
        .route(GET("/hello").and(accept(MediaType.APPLICATION_JSON)), greetingHandler::hello);
  }

   */

}
