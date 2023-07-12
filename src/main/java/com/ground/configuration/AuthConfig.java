package com.ground.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ground.data.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

@Configuration
public class AuthConfig {

  /**
   * 사용자 정보를 조회하는 서비스
   * <p>
   * @return ReactiveUserDetailsService
   */
  @Bean
  public ReactiveUserDetailsService userDetailsService(UserRepository userRep) {
    return new ReactiveUserDetailsService() {
      @Override
      public Mono<UserDetails> findByUsername(String username) {
        return userRep.findById(username).cast(UserDetails.class);
      }
    };
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    PasswordEncoderFactories.createDelegatingPasswordEncoder();
    return new BCryptPasswordEncoder();
  }

}
