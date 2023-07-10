package com.ground.data.models.supports;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Auditable;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.Instant;
import java.util.Optional;

@Accessors(prefix = "_")
@SuperBuilder
@Setter
@NoArgsConstructor
public abstract class Basic<I extends Serializable> implements Auditable<String, I, Instant> {


  @Getter
  @Id
  private I _id;

  @Field("createdBy")
  private String _createdBy;
  @Field("createdAt")
  private Instant _createdDate;
  @Field("updatedBy")
  private String _lastModifiedBy;
  @Field("updatedAt")
  private Instant _lastModifiedDate;

  @Getter
  @Transient
  @Builder.Default
  private boolean _new = false;

  @Override
  public Optional<String> getCreatedBy() {
    return Optional.ofNullable(_createdBy);
  }

  @Override
  public Optional<Instant> getCreatedDate() {
    return Optional.ofNullable(_createdDate);
  }

  @Override
  public Optional<String> getLastModifiedBy() {
    return Optional.ofNullable(_lastModifiedBy);
  }

  @Override
  public Optional<Instant> getLastModifiedDate() {
    return Optional.ofNullable(_lastModifiedDate);
  }


}
