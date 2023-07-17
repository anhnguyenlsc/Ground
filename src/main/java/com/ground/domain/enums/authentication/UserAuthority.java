package com.ground.domain.enums.authentication;

import org.springframework.security.core.GrantedAuthority;

public enum UserAuthority implements GrantedAuthority {
  ANONYMOUS, USER, ADMIN;

  @Override
  public String getAuthority() {
    return name();
  }
}
