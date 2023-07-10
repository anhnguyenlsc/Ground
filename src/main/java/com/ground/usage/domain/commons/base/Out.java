package com.ground.usage.domain.commons.base;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
public class Out<T> {

  private final String message;
  private final String link;
  private final T data;

}
