package com.ground.usage.cases.port.api.user;

import com.ground.usage.values.out.UserOUT;
import reactor.core.publisher.Mono;

public interface UserUC {

  /**
   *
   * @param username
   * @return
   */

  public Mono<String> getUserId(String username);



}
