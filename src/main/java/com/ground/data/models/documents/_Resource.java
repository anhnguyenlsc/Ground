package com.ground.data.models.documents;

import com.ground.data.models.supports.Variant;
import com.ground.domain.enums.resource.ResourceProperty;
import com.ground.domain.enums.resource.ResourceStatus;
import com.ground.domain.enums.resource.ResourceType;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@SuperBuilder
@Data
public class _Resource extends Variant<Long, ResourceType, ResourceStatus, ResourceProperty> {

  @DBRef
  private _User user;
  @DBRef
  private _Circle circle;

  private String name;
  private String description;
  private String unit;
  private Integer amount;
  private Integer price;

  //reserved
  //서비스일경우 제공 가능 일정(reserved)
  //제품일경우 출하 예정
  private List<Object> schedules;

  //서비스 목록, 서비스는 자원이 될 수 있음
  private List<Object> services;

  private Map<String,String> catalogues;

}
