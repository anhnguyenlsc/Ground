package com.ground.data.models.documents;

import com.ground.data.models.supports.Standard;
import com.ground.domain.enums.activity.ActivityStatus;
import com.ground.domain.enums.activity.ActivityType;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.DBRef;


/**
 * 활동은 서클과 유저간의 소통을 위한 도구이다.
 * <br/>
 * Activity is a tool for communication between circles and users.
 * <br/>
 */
@Data
@SuperBuilder
//@Document
public class _Activity extends Standard<Long, ActivityType, ActivityStatus> {

  protected ActivityType type;

  private ActivityStatus status;

  //노출 여부 - 개인적인 활동인지, 공개적인 활동인지
  private boolean exposure;
  // 관련 이슈
  @DBRef(lazy = true)
  private _Issue issue;
  @DBRef(lazy = true)
  private _Activity ReactionRef;


}
