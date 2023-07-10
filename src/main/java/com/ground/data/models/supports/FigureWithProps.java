package com.ground.data.models.supports;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class FigureWithProps<I extends Serializable, T extends Enum, P extends Enum, S extends Enum> extends BasicDocument<I> implements Figure<T, S> {

private T type;
@Singular
private Map<P, Object> properties;
private S status;

}
