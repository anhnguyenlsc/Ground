package com.ground.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {


  //@Bean
  public MapReactiveUserDetailsService userDetailsService() {
    UserDetails user = User.withDefaultPasswordEncoder()
        .username("user")
        .password("password")
        .roles("USER")
        .build();
    return new MapReactiveUserDetailsService(user);
  }

  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
    return serverHttpSecurity
    .authorizeExchange(authorizeExchangeSpec -> {
      authorizeExchangeSpec
      //.pathMatchers("/actuator/**").permitAll()
      //.pathMatchers("/api/**").hasRole("USER")
      .pathMatchers(HttpMethod.PUT).authenticated()
      .pathMatchers(HttpMethod.POST).authenticated()
      .pathMatchers(HttpMethod.DELETE).authenticated()
      .pathMatchers(HttpMethod.PATCH).authenticated()
      .anyExchange().permitAll();
    })
    .csrf(csrfSpec -> {
      csrfSpec.disable();
    })
    .cors(corsSpec -> {
      corsSpec.disable();
    })
    .httpBasic(httpBasicSpec -> {
      httpBasicSpec.disable();
    })
    .formLogin(formLoginSpec -> {
      formLoginSpec.disable();
    })
    .build();
  }
}

