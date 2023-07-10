package com.ground.data.models.supports;

import lombok.Data;

@Data(staticConstructor = "of")
public class AmountRange implements Range<Integer, Integer> {


  private final Integer from;
  private final Integer to;


}
