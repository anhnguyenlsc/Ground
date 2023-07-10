package com.ground.data.models.supports;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class RateAndMax {

  private final float rate;
  private final Integer max;

}

