package com.ground.services;

import com.ground.usage.cases.user.SignIn;
import com.ground.usage.cases.user.SignUp;
import com.ground.usage.cases.user.profile.EditProfile;
import com.ground.usage.values.base.Out;
import com.ground.usage.values.in.EditableProfile;
import com.ground.usage.values.in.SignInUser;
import com.ground.usage.values.in.SignUpUser;
import com.ground.usage.values.out.User;
import reactor.core.publisher.Mono;

public class UserService implements SignIn, SignUp, EditProfile {

  @Override
  public Mono<User> in(SignInUser signInUser) {
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
  public Mono<User> up(SignUpUser signUpUser) {
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
  public Mono<Out<User>> viewProfile() {
    return null;
  }

  @Override
  public Mono<Out<User>> viewProfile(String id) {
    return null;
  }

  @Override
  public Mono<Out<User>> editProfile(EditableProfile editableProfile) {
    return null;
  }
}
