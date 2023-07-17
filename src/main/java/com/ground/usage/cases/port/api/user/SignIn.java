package com.ground.usage.cases.port.api.user;

import com.ground.usage.values.base.Out;
import com.ground.usage.values.in.SignInUserIN;
import com.ground.usage.values.out.UserOUT;
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
  public Mono<UserOUT> in(SignInUserIN signInUser);

  /**
   * ID 찾기
   */
  public Mono<Out<String>> findIdByEmail(String email);

  /**
   * 비밀번호 찾기
   */
  public Mono<Out<String>> findPasswordById(String id);



}
