package com.ground.data.models.documents;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ground.domain.enums.authentication.UserAuthority;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Collection;

@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class _Usser implements Persistable<String> {

    @Id
    @EqualsAndHashCode.Include
    String id;

    String avatar;

    @Builder.Default
    boolean expired = false;

    @Builder.Default
    boolean locked = false;

    String userId;
    String email;
    String password;
    String nickname;

    Long member;

    String name;
    @Singular
    Collection<UserAuthority> us_authorities;

    String provider;

    String ip;

    @Builder.Default
    Integer loginCount = 0;

    @CreatedDate
    Instant createdAt;

    @LastModifiedDate
    Instant modifiedAt;

    @Override
    public boolean isNew() {
        return createdAt == null;
    }
}
