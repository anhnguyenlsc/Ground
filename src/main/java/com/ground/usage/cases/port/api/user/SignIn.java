package com.ground.usage.cases.port.api.user;

import com.ground.usage.values.base.TransferOut;
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
  public Mono<TransferOut<String>> findIdByEmail(String email);

  /**
   * 비밀번호 찾기
   */
  public Mono<TransferOut<String>> findPasswordById(String id);



}
