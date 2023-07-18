package com.ground.usage.cases.port.bot;

import com.ground.usage.values.base.TransferOut;
import com.ground.usage.values.in.ApplyCircleIN;
import com.ground.usage.values.out.CircleOUT;
import reactor.core.publisher.Mono;

public interface JoinCircle {



  public Mono<TransferOut<CircleOUT>> step1(ApplyCircleIN circle);
  public Mono<TransferOut<CircleOUT>> step2(ApplyCircleIN circle);
  public Mono<TransferOut<CircleOUT>> step3(ApplyCircleIN circle);

}
