package com.ground.data.mappers.supports;



import com.ground.data.models.supports.Basic;

import java.io.Serializable;

public interface BasicMapper<I extends Serializable, D extends Basic<I>, T> {


  D to(T t);

  T to(D d);


}
