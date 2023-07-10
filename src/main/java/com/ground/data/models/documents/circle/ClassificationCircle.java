package com.ground.data.models.documents.circle;

import com.ground.data.models.documents._Circle;
import com.ground.data.models.supports.Standard;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * 서클을 그룹화해서 분류
 * 사용자에게 보다 좋은 접근성을 제공한다.
 * <p>
 * 일정 주기별로 자동 생성되거나 관리자가 직접 생성할 수 있다.
 * <p>
 * Categorize circles by grouping them
 * Provide better accessibility to users.
 * <p>
 * It can be automatically generated at regular intervals or created directly by the administrator.
 */
@SuperBuilder
@Data
public class ClassificationCircle extends Standard<Long, ClassificationCircle.Type, ClassificationCircle.Status> {

  private String circleName;
  private String description;

  @DBRef(lazy = true)
  private _Circle circle;

  private Type type;
  private Status status;


  public enum Type {
    best,
    controversial,
    hot,
    newest,
    random,
    rising,
    top,
    event,
  }

  public enum Status {}

}
