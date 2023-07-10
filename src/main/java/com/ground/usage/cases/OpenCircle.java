package com.ground.usage.cases;


import com.ground.usage.values.in.ApplyCircle;
import com.ground.usage.values.in.AproveCircle;
import com.ground.usage.values.in.RejectCircle;
import com.ground.usage.values.out.Circle;
import reactor.core.publisher.Mono;

/**
 * 서클을 개설하는 유스케이스
 * <p>
 * 1. 서클을 개설신청한다.
 * <br/>
 * 2. 서클을 개설신청을 승인한다.
 * <br/>
 * 3. 서클을 개설신청을 거절한다.
 * <p>
 * <p>
 * Use case to open a circle
 * <p>
 * 1. Apply to open a circle.
 * <br/>
 * 2. Approve the application to open a circle.
 * <br/>
 * 3. Decline the application to open a circle.
 */

public interface OpenCircle {

  public Mono<Circle> apply(ApplyCircle applyCircle);

  public Mono<Circle> aprove(AproveCircle aproveCircle);

  public Mono<Circle> reject(RejectCircle rejectCircle);

}
