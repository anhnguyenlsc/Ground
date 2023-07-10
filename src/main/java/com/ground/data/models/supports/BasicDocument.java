package com.ground.data.models.supports;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Auditable;

import java.io.Serializable;
import java.time.Instant;
import java.util.Optional;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
public class BasicDocument<I extends Serializable> implements Auditable<String, I, Instant> {


    @Id
    private I id;

private String createdBy;

private Instant createdDate;

private String lastModifiedBy;

private Instant lastModifiedDate;

@Getter(AccessLevel.NONE)
@Transient
@Builder.Default
private boolean _new = false;

@Override
public Optional<String> getCreatedBy() {
    return Optional.ofNullable(createdBy);
}

@Override
public Optional<Instant> getCreatedDate() {
    return Optional.ofNullable(createdDate);
}

@Override
public Optional<String> getLastModifiedBy() {
    return Optional.ofNullable(lastModifiedBy);
}

@Override
public Optional<Instant> getLastModifiedDate() {
    return Optional.ofNullable(lastModifiedDate);
}

@Override
public I getId() {
    return id;
}

@Override
public boolean isNew() {
    return this._new;
}
}
