package com.ground.data.models.supports;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class FigureDocument<I extends Serializable, T extends Enum,S extends Enum> extends BasicDocument<I> implements Figure<T, S> {

private T type;
private S status;

}
