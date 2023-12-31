package com.ground.configuration;

import com.ground.data.models.documents._Member;
import com.ground.services.dtos.AuthenticationDto;
import com.ground.support.AuthenticationSuccessHandler;
import com.ground.support.JwtAuthentication;
import com.ground.support.JwtWebFilter;
import com.ground.support.SecurityUtils;
import com.ground.usage.values.authentication.In;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.security.Principal;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration(proxyBeanMethods = false)
@EnableWebFluxSecurity
//@EnableSpringWebSession
@EnableReactiveMethodSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {
    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    JwtWebFilter jwtWebFilter;

    @Autowired
    ReactiveMongoTemplate template;

  //A ServerHttpSecurity is similar to Spring Security's HttpSecurity but for WebFlux
  @Bean
  public SecurityWebFilterChain securityWebFilterChain(
      ServerHttpSecurity serverHttpSecurity,
      @Qualifier("JwtAuthenticationWebFilter") AuthenticationWebFilter authenticationWebFilter
  ) {

    return serverHttpSecurity
        .authorizeExchange(authorizeExchangeSpec -> {
          authorizeExchangeSpec
              .pathMatchers("/actuator/**").permitAll()
              .pathMatchers("/authentication/**").permitAll()
              .pathMatchers("/api/authentication/**").permitAll()
              .pathMatchers(HttpMethod.GET).authenticated()
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
            corsSpec.configurationSource(source -> {
                CorsConfiguration cors = new CorsConfiguration().applyPermitDefaultValues();
                cors.addAllowedMethod(HttpMethod.DELETE);
                cors.addAllowedMethod(HttpMethod.PUT);
                return cors;
            });
        })
        .httpBasic(httpBasicSpec -> {
          httpBasicSpec.disable();
          // to disable http basic login popup at browser end when the jwt is expired
          httpBasicSpec.authenticationEntryPoint(new HttpStatusServerEntryPoint(HttpStatus.UNAUTHORIZED));
        })
        .formLogin(formLoginSpec -> {
          formLoginSpec.disable();
          //formLoginSpec.loginPage("/authentication/in");
        })
        .addFilterBefore(jwtWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
        .oauth2Login(oauth2LoginSpec -> {
            oauth2LoginSpec.authenticationSuccessHandler(authenticationSuccessHandler);
        })

//        .logout(logoutSpec -> {
//            logoutSpec.logoutUrl("http://localhost:5173");
//            logoutSpec.logoutSuccessHandler() //TODO: Configure logout
//        })
        .build();
  }


  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  //@Bean
  public ReactiveAuthenticationManager reactiveAuthenticationManager(ReactiveUserDetailsService userDetailsService,
                                                                     PasswordEncoder passwordEncoder) {
    var authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
    authenticationManager.setPasswordEncoder(passwordEncoder);
    return authenticationManager;
  }


  @Bean("JwtAuthenticationWebFilter")
  public AuthenticationWebFilter authenticationWebFilter(SecurityUtils securityUtils) {


    //1. Matcher를 통해 인증이 필요한지 확인 - Check if authentication is required through Matcher를
    final ServerWebExchangeMatcher serverWebExchangeMatcher = exchange -> {
      return Mono.justOrEmpty(exchange)
          .flatMap(_exchange -> Mono.justOrEmpty(_exchange.getRequest().getHeaders().getFirst("Authorization")))
          .filter(jwt -> !jwt.isEmpty() && jwt.startsWith("Bearer "))
          .flatMap(jwt -> ServerWebExchangeMatcher.MatchResult.match())
          .switchIfEmpty(ServerWebExchangeMatcher.MatchResult.notMatch());
    };

    //2. 인증을 위한 정보를 추출 - Extract information for authentication
    final ServerAuthenticationConverter serverAuthenticationConverter = exchange -> {
      return Mono.justOrEmpty(exchange)
          .flatMap(_exchange -> Mono.justOrEmpty(_exchange.getRequest().getHeaders().getFirst("Authorization")))
          .filter(jwt -> !jwt.isEmpty() && jwt.startsWith("Bearer "))
          .map(jwt -> {
            try {
              return securityUtils.decode(jwt.replace("Bearer ", ""));
            }

            catch (Exception e) {
              var __ = new UsernamePasswordAuthenticationToken("anonymous", "anonymous");
              __.setAuthenticated(false);
              return __;
            }
          });
    };

    //3. 인증을 위한 AuthenticationManager - AuthenticationManager for Authentication
    final ReactiveAuthenticationManager authenticationManager = authentication -> {
      return Mono.just(authentication);
    };

    var authenticationWebFilter = new AuthenticationWebFilter(authenticationManager);
    authenticationWebFilter.setServerAuthenticationConverter(serverAuthenticationConverter);
    authenticationWebFilter.setRequiresAuthenticationMatcher(serverWebExchangeMatcher);
    return authenticationWebFilter;
  }


  // Get the JWT from client through API : api/authentication
  @Bean
  public RouterFunction<ServerResponse> userRoutes() {
      return route().GET("/api/authentication", this::authenticationRes).build();
  }


    public Mono<ServerResponse> authenticationRes(ServerRequest request) {
        return request.principal().map(this::authentication).flatMap(m -> ok().body(m, AuthenticationDto.class));
    }

    public Mono<AuthenticationDto> authentication(Principal principal) {
        var authentication = (JwtAuthentication) principal;
        var id = authentication.getPrincipal().getId();

        return Mono.just(AuthenticationDto.builder().token(authentication.getCredentials()).build())
                .flatMap(it -> template.findById(id, _Member.class)
                        .doOnNext(it::setPrincipal).doOnNext(System.out::println)
                        .thenReturn(it));
    }


  @Bean
  public RouterFunction<ServerResponse> authenticationRoute() {
    final ReactiveAuthenticationManager authenticationManager = authentication -> {
        log.debug("authenticationManager: {}", authentication);
      return Mono.just(authentication);
    };


    /*
            authenticationManager = authentication -> Flux
                .fromIterable(Arrays.asList(jwtAuthenticationProvider,
                        opaqueTokenAuthenticationProvider))
                .concatMapDelayError(reactiveAuthenticationManager -> reactiveAuthenticationManager
                        .authenticate(authentication)
                .next();
     */


    return route().nest(path("/api/authentication"), builder -> {
          builder.GET("/in", request -> {
            return request.bodyToMono(In.class)
                .flatMap(in -> {
                  return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                          in.getUsername(), in.getPassword()))
                      .flatMap(authentication -> {
                        log.debug("authentication: {}", authentication);
                        return ok().render("authentication/in");
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
            return ok().render("authentication/up");
          });

          builder.build();
        })
        .build();
  }

}

