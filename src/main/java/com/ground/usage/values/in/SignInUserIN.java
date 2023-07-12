package com.ground.usage.values.in;

import lombok.Value;

@Value(staticConstructor = "of")
public class SignInUserIN {

  private String id;
  private String password;

  private Boolean rememberMe;
}
