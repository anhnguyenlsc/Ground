package com.ground.usage.cases.user;

import com.ground.usage.values.base.Out;
import com.ground.usage.values.in.SignInUser;
import com.ground.usage.values.out.User;
import reactor.core.publisher.Mono;

/**
 * 로그인 유스케이스
 *
 *
 */

public interface SignIn {

  /**
   * 로그인
   */
  public Mono<User> in(SignInUser signInUser);

  /**
   * ID 찾기
   */
  public Mono<Out<String>> findIdByEmail(String email);

  /**
   * 비밀번호 찾기
   */
  public Mono<Out<String>> findPasswordById(String id);



}
