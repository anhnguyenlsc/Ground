package com.ground.services;

import com.ground.data.repositories.UserRepository;
import com.ground.usage.cases.port.api.user.SignUpUC;
import com.ground.usage.values.base.TransferOut;
import com.ground.usage.values.out.UserOUT;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements SignUpUC{

  private final ReactiveMongoTemplate mongoOps;
  private final UserRepository userRep;

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
}
