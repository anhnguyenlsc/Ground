package com.ground.data.models.documents;

import com.ground.data.models.supports.Standard;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * 드레프트가 현실화(Issue) 되는 조건 :: 시간, 자원, 한도 ....
 * 1회성인지, 반복인지, 항상 가능한지
 * 이슈 주기 : anytime,...min , ...hour, ...day, ...week, ...month, ...year
 * 소스 : from mq , self , api , ...
 */

@Data
@SuperBuilder
public class _Draft extends Standard<Long,  _Draft.Type,  _Draft.Status> {


  private Type type;

  private Status status;

  public enum Type  {}
  public enum Status  {}

}
