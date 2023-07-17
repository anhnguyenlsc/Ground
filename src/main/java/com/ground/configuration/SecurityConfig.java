package com.ground.configuration;

import com.ground.data.repositories.UserRepository;
import com.ground.usage.values.authentication.In;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;
import org.springframework.session.ReactiveMapSessionRepository;
import org.springframework.session.ReactiveSessionRepository;
import org.springframework.session.config.annotation.web.server.EnableSpringWebSession;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@EnableWebFluxSecurity
@EnableSpringWebSession
@EnableReactiveMethodSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

  @Bean
  public ReactiveSessionRepository reactiveSessionRepository() {
    return new ReactiveMapSessionRepository(new ConcurrentHashMap<>());
  }

  @Bean
  public ReactiveUserDetailsService userDetailsService(UserRepository userRep) {
    return new ReactiveUserDetailsService() {
      @Override
      public Mono<UserDetails> findByUsername(String username) {
        return userRep.findById(username).cast(UserDetails.class);
      }
    };
  }

  //@Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public ReactiveAuthenticationManager reactiveAuthenticationManager(){
    return authentication -> {

      authentication.setAuthenticated(true);
      log.info("authentication: {}", authentication);
      return Mono.just(authentication);
    };
  }

  //@Bean
  public ReactiveAuthenticationManager authenticationManager(ReactiveUserDetailsService userDetailService) {
    UserDetailsRepositoryReactiveAuthenticationManager authenticationManager =
        new UserDetailsRepositoryReactiveAuthenticationManager(userDetailService);
    authenticationManager.setPasswordEncoder(passwordEncoder());
    return authenticationManager;
  }


  //A ServerHttpSecurity is similar to Spring Security's HttpSecurity but for WebFlux
  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(
      ServerHttpSecurity serverHttpSecurity,
      ReactiveAuthenticationManager reactiveAuthenticationManager,
      ReactiveUserDetailsService reactiveUserDetailsService)
  {
    return serverHttpSecurity
    .authorizeExchange(authorizeExchangeSpec -> {
      authorizeExchangeSpec
      .pathMatchers("/actuator/**").permitAll()
      .pathMatchers("/authentication/**").permitAll()
      .pathMatchers("/api/authentication/**").permitAll()
      .pathMatchers(HttpMethod.PUT).authenticated()
      .pathMatchers(HttpMethod.POST).authenticated()
      .pathMatchers(HttpMethod.DELETE).authenticated()
      .pathMatchers(HttpMethod.PATCH).authenticated()
      .anyExchange().authenticated();
    })
    .csrf(csrfSpec -> {
      csrfSpec.disable();
    })
    .cors(corsSpec -> {
      corsSpec.disable();
    })
    .httpBasic(httpBasicSpec -> {
      httpBasicSpec.disable();
      // to disable http basic login popup at browser end when the jwt is expired
      //httpBasicSpec.authenticationEntryPoint(new HttpStatusServerEntryPoint(HttpStatus.UNAUTHORIZED));
    })
    .formLogin(formLoginSpec -> {
      //formLoginSpec.disable();
      //formLoginSpec.loginPage("/authentication/in");
    })
    .oauth2Login(oauth2LoginSpec -> {
    })
    //.addFilterAt(new AuthenticationWebFilter(reactiveAuthenticationManager), SecurityWebFiltersOrder.REACTOR_CONTEXT)
    .build();
  }




  @Bean
  public RouterFunction<ServerResponse> authenticationRoute(ReactiveAuthenticationManager authenticationManager) {

    /*
            authenticationManager = authentication -> Flux
                .fromIterable(Arrays.asList(jwtAuthenticationProvider,
                        opaqueTokenAuthenticationProvider))
                .concatMapDelayError(reactiveAuthenticationManager -> reactiveAuthenticationManager
                        .authenticate(authentication)
                .next();
     */


    return route().nest(path("/api/authentication"), builder -> {
      builder.POST("/in", request -> {

        return request.bodyToMono(In.class)
        .flatMap(in -> {

          return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
              in.getUsername(), in.getPassword()))
              .flatMap(authentication -> {
                log.info("authentication: {}", authentication);
                return ServerResponse.ok().render("authentication/in");
              });

        });




        /*

         Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        request.bodyToMono(In.class)
            .map(in -> {
              //authenticationManager.authenticate()
            });
*/
        //authenticationManager.authenticate(request.body());


        //authenticationManager.authenticate(request);

      });
      builder.POST("/up", request -> {
        return ServerResponse.ok().render("authentication/up");
      });

      builder.build();
    })
    .build();
  }
}

