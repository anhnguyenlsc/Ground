package com.ground.services;

import com.ground.usage.cases.port.web.user.SignIn;
import com.ground.usage.cases.port.web.user.SignUp;
import com.ground.usage.cases.port.web.user.profile.EditProfile;
import com.ground.usage.values.base.Out;
import com.ground.usage.values.in.EditableProfileIN;
import com.ground.usage.values.in.SignInUserIN;
import com.ground.usage.values.in.SignUpUserIN;
import com.ground.usage.values.out.UserOUT;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService implements SignIn, SignUp, EditProfile {

  private final ReactiveMongoTemplate MongoOps;


  @Override
  public Mono<UserOUT> in(SignInUserIN signIn) {



    //SignInUserIN.of("","");

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
  public Mono<UserOUT> up(SignUpUserIN signUpUser) {
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

  @Override
  public Mono<Out<UserOUT>> viewProfile() {
    return null;
  }

  @Override
  public Mono<Out<UserOUT>> viewProfile(String id) {
    return null;
  }

  @Override
  public Mono<Out<UserOUT>> editProfile(EditableProfileIN editableProfile) {
    return null;
  }
}
