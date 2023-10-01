package com.ground.data.models.documents;


import com.ground.data.models.enums.CircleStatus;
import com.ground.data.models.supports.embedded.CircleEmbedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Document(collection = "_Circle")
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class _Circcle {
    @Id
    private long id;
    long parent;
    long pyramid;

    private String description;
    private String avatar;

    private String typeId;
    private String categoryId;
    private String tags;

    private String name;
    private String banner;
    private Object rules;

    private String typeCircle;

    private _Member owner;
    List<_Member> membersCounts = new ArrayList<>();

    CircleStatus status = CircleStatus.OPEN;
    Set<CircleEmbedded> children;
    Set<CircleEmbedded> pyramids;

    @CreatedDate
    Instant createdAt = Instant.now();

    @LastModifiedDate
    Instant modifiedAt;
}
