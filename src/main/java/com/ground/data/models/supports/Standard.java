package com.ground.data.models.supports;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@NoArgsConstructor
public abstract class Standard<I extends Serializable, T extends Enum, S extends Enum> extends Basic<I> {


  public abstract T getType();

  public abstract S getStatus();


}
