package com.ground.usage.values.base;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;


@Value(staticConstructor = "of")
@Builder
public class TransferOut<T> {

  private final Status status;
  private final String message;
  private final String link;
  private final T data;

  public static enum Status {
    SUCCESS, FAIL, ERROR
  }

}
