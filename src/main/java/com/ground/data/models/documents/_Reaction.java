package com.ground.data.models.documents;

import com.ground.data.models.supports.Standard;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@SuperBuilder
//@Document
public class _Reaction extends Standard<Long, _Reaction.Type, _Reaction.Status> {

  protected Type type;

  private Status status;

  //노출 여부 - 개인적인 활동인지, 공개적인 활동인지
  private boolean exposure;
  // 관련 이슈
  @DBRef(lazy = true)
  private _Issue issue;
  @DBRef(lazy = true)
  private _Reaction ReactionRef;

  public enum Type {comment, order, bet}
  public enum Status {}


}
