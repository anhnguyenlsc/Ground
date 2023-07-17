package com.ground.services;

import com.ground.data.repositories.UserRepository;
import com.ground.usage.cases.port.api.user.SignIn;
import com.ground.usage.cases.port.api.user.SignUpUC;
import com.ground.usage.values.base.Out;
import com.ground.usage.values.in.SignInUserIN;
import com.ground.usage.values.out.UserOUT;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements SignIn, SignUpUC {

  private final ReactiveMongoTemplate mongoOps;
  private final UserRepository userRep;

  @Override
  public Mono<UserOUT> in(SignInUserIN signInUser) {
    return null;
  }

  @Override
  public Mono<Out<String>> findIdByEmail(String email) {
    return null;
  }

  @Override
  public Mono<Out<String>> findPasswordById(String id) {
    return null;
  }

  @Override
  public Mono<UserOUT> up(InStep1 signUpUser) {
    return null;
  }

  @Override
  public Mono<Out<String>> verifyBySms(String phoneNumber) {
    return null;
  }

  @Override
  public Mono<Out<String>> verifyByEmail(String email) {
    return null;
  }

  @Override
  public Mono<Boolean> isItValidID(String id) {
    return null;
  }

  @Override
  public Mono<Boolean> isItValidName(String name) {
    return null;
  }
}
