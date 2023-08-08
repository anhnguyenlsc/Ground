package com.ground.usage.values.authentication;

import lombok.Builder;
import lombok.Value;


@Builder
@Value(staticConstructor = "of")
public class In {
  private String username;
  private String password;
}
