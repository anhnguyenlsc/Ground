package com.ground.configuration;

import com.ground.support.SnowflakeIDGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableAsync
@EnableScheduling
public class ApplicationConfig {
  @Bean
  public SnowflakeIDGenerator snowflakeIDGenerator() {
    return new SnowflakeIDGenerator(0);
  }
}
