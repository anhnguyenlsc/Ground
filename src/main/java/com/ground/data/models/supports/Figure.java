package com.ground.data.models.supports;

public interface Figure<T extends Enum, S extends Enum> {

T getType();

S getStatus();

}
