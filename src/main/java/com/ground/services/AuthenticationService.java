package com.ground.services;

import com.ground.data.repositories.UserRepository;
import com.ground.support.SecurityUtils;
import com.ground.usage.cases.port.api.user.SignInUC;
import com.ground.usage.cases.port.api.user.SignUpUC;
import com.ground.usage.values.base.TransferOut;
import com.ground.usage.values.in.SignInIN;
import com.ground.usage.values.out.UserOUT;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements SignInUC, SignUpUC{

  private final ReactiveMongoTemplate mongoOps;
  private final UserRepository userRep;
  private final BCryptPasswordEncoder passwordEncoder;
  private final SecurityUtils utils;

  @Override
  public Mono<UserOUT> in(SignInIN signIn) {
    return userRep.findById(signIn.getUsername())
    .flatMap(user -> {
      if(passwordEncoder.matches(signIn.getPassword(), user.getPassword())){
        return Mono.just(null);
      }
      else{
        return Mono.error(new RuntimeException("비밀번호가 일치하지 않습니다."));
      }
    });
  }

  @Override
  public Mono<TransferOut<String>> up(InStep1 in) {
    return null;
  }

  @Override
  public Mono<TransferOut<String>> verifyBySms(String phoneNumber) {
    return null;
  }

  @Override
  public Mono<TransferOut<String>> verifyByEmail(String email) {
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



  @Override
  public Mono<TransferOut<String>> findIdByEmail(String email) {
    return null;
  }

  @Override
  public Mono<TransferOut<String>> findPasswordById(String id) {
    return null;
  }
}
