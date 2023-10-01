package com.ground.data.models.documents;

import com.ground.data.models.supports.Standard;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * 이슈는 드레프트가 현실화 되는 조건을 만족해야 한다.
 * post , 상품, 서비스, 게임 ...
 * <br>
 * The issue must meet the conditions under which the draft becomes a reality.
 * Post, goods, services, games...
 *
 */
@Data
@SuperBuilder
public class _Issue extends Standard<Long, _Issue.Type, _Issue.Status> {

  private Type type;

  private Status status;


  public enum Type  {}
  public enum Status  {}
}
