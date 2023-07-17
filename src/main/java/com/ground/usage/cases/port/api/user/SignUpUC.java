package com.ground.usage.cases.port.api.user;

import com.ground.usage.values.base.Out;
import com.ground.usage.values.out.UserOUT;
import lombok.Value;
import reactor.core.publisher.Mono;

/**
 * 회원가입 유스케이스
 *
 * this_is_a_use_case_to_sign_up_for_membership
 *
 * 1. 절차(workflow)
 *   -goto signup page
 *   -input phone number( activity)
 *     -case 1: send to zalo to verify
 *     -case 2: send to email to verify
 *     -case 3: send to phone to verify
 *     -case 4: cancel
 *
 *
 */

public interface SignUpUC {

  /**
   * 등록
   */
  public Mono<UserOUT> up(InStep1 in);

  public Mono<Out<String>> verifyBySms(String phoneNumber);
  public Mono<Out<String>> verifyByEmail(String email);
  /**
   * 중복확인
   */
  public Mono<Boolean> isItValidID(String id);

  /**
   * 중복확인
   */
  public Mono<Boolean> isItValidName(String name);

  @Value(staticConstructor = "of")
  public static class InStep1 {

    private String idOrEmail;
    private String password;
    private String name;
    private String phoneNumber;
    private String email;

  }
  @Value(staticConstructor = "of")
  public static class InStep2 {

    private String idOrEmail;
    private String password;
    private String name;
    private String phoneNumber;
    private String email;

  }

}
