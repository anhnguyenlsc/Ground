package com.ground.usage.cases.port.bot;

import com.ground.usage.values.base.Out;
import com.ground.usage.values.in.ApplyCircleIN;
import com.ground.usage.values.out.CircleOUT;
import reactor.core.publisher.Mono;

public interface JoinCircle {



  public Mono<Out<CircleOUT>> step1(ApplyCircleIN circle);
  public Mono<Out<CircleOUT>> step2(ApplyCircleIN circle);
  public Mono<Out<CircleOUT>> step3(ApplyCircleIN circle);

}
