package com.ground.usage.values.in;

import lombok.Value;

@Value(staticConstructor = "of")
public class SignInUserIN {

  private String email;
  private String password;

}
