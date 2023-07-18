package com.ground.services;

import com.ground.data.repositories.UserRepository;
import com.ground.usage.cases.port.api.user.profile.EditProfile;
import com.ground.usage.values.base.TransferOut;
import com.ground.usage.values.in.EditableProfileIN;
import com.ground.usage.values.out.UserOUT;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService implements EditProfile {

  private final ReactiveMongoTemplate mongoOps;
  private final UserRepository userRep;


  @Override
  @PreAuthorize("hasRole('USER')")
  public Mono<TransferOut<UserOUT>> viewProfile() {
    return null;
  }

  @Override
  public Mono<TransferOut<UserOUT>> viewProfile(String id) {
    return null;
  }

  @Override
  public Mono<TransferOut<UserOUT>> editProfile(EditableProfileIN editableProfile) {
    return null;
  }
}
