package com.ground.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.result.view.MustacheViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.ExchangeStrategies;

import java.time.Instant;

@Configuration
@Slf4j
@RequiredArgsConstructor
@EnableWebFlux
public class WebFluxConfig implements WebFluxConfigurer {

  private final MustacheViewResolver resolver;

  @Override
  public void configureViewResolvers(ViewResolverRegistry registry) {
    registry.viewResolver(resolver);
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**").allowedOrigins("http://localhost:5173/#/").allowedMethods("GET", "POST", "PUT", "DELETE").maxAge(3600); //http://192.168.0.10:5173/#/
  }

  @Override
  public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {

    SimpleModule module = new SimpleModule();
    module.addSerializer(Long.class, ToStringSerializer.instance);
    module.addSerializer(long.class, ToStringSerializer.instance);
    module.addSerializer(Instant.class, ToStringSerializer.instance);

    var objectMapper = new ObjectMapper()
    .setSerializationInclusion(JsonInclude.Include.NON_NULL)
    .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
    .setSerializationInclusion(JsonInclude.Include.NON_DEFAULT)
    .setSerializationInclusion(JsonInclude.Include.NON_ABSENT)
    .registerModule(module);


    configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper));

    WebFluxConfigurer.super.configureHttpMessageCodecs(configurer);
  }

  @Bean
  public ExchangeStrategies strategies(ObjectMapper objectMapper) {
    Jackson2JsonEncoder encoder = new Jackson2JsonEncoder(objectMapper);
    Jackson2JsonDecoder decoder = new Jackson2JsonDecoder(objectMapper);

    ExchangeStrategies strategies = ExchangeStrategies.builder()
    .codecs(codecs -> {
      codecs.defaultCodecs().maxInMemorySize(128 * 1024 * 1024);

      codecs.defaultCodecs().jackson2JsonEncoder(encoder);
      codecs.defaultCodecs().jackson2JsonDecoder(decoder);
    })
    .build();

    return strategies;
  }


}
