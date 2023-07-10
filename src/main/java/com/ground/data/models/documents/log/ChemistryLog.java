package com.ground.data.models.documents.log;

import com.ground.data.models.documents._Chemistry;
import com.ground.data.models.documents._User;
import com.ground.data.models.supports.Standard;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * 세상의 모든것은 관계이다.
 * 이것은 그 인간들 사이의 관계의 깊이를 표현한다.
 *
 * Everything in the world is a relationship.
 * This expresses the depth of the relationship between those human beings.
 *
 */
//@Document
@SuperBuilder
@Data
public class ChemistryLog extends Standard<Long, _Chemistry.Type, _Chemistry.Status> {

  private _User from;
  private _User to;
  private int value;

  private _Chemistry.Type type;
  private _Chemistry.Status status;


}
