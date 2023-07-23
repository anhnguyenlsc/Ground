package com.ground.usage.values.in;

import lombok.Value;

@Value(staticConstructor = "of")
public class SignInIN {

  private String username;
  private String password;

  private Boolean rememberMe;
}
