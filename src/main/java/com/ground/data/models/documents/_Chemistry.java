package com.ground.data.models.documents;

import com.ground.data.models.supports.Standard;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;

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
public class _Chemistry extends Standard<String, _Chemistry.Type, _Chemistry.Status> {

  @DBRef(lazy = true)
  private _User from;
  @DBRef(lazy = true)
  private _User to;

  private int value;

  private Type type;
  private Status status;

  public enum Type  {}
  public enum Status  {}


  {
    ArrayList<String> list = new ArrayList<>();
  }

}
