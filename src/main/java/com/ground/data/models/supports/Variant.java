package com.ground.data.models.supports;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Map;

@SuperBuilder
@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = true)
public class Variant<I extends Serializable, T extends Enum, S extends Enum, P extends Enum> extends Basic<I> {


  private T _type;
  private S _status;

  @Singular
  private Map<P, Object> _properties;


}


