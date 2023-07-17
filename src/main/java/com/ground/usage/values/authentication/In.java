package com.ground.usage.values.authentication;

import lombok.Value;

@Value(staticConstructor = "of")
public class In {
  private String username;
  private String password;
}
