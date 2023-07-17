package com.ground.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import reactor.core.publisher.Mono;

import static com.ground.support.SecurityUtils.getCurrentUserLogin;

@Configuration
@EnableReactiveMongoAuditing
@EnableReactiveMongoRepositories(basePackages = "com.ground.data.repositories")
class ReactiveMongoConfig {

  @Bean
  ReactiveAuditorAware<String> auditorAware() {
    return () -> getCurrentUserLogin().switchIfEmpty(Mono.just("system"));
  }

}
