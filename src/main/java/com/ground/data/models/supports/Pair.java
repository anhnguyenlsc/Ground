package com.ground.data.models.supports;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.sql.Array;

public class Pair<A, B> implements Serializable {

  private final Object[] pair = new Object[2];

  public Pair(A first, B second) {
    pair[0] = first;
    pair[1] = second;
  }

  public A getFirst() {
    return (A) pair[0];

  }


}
