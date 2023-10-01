package com.ground.data.models.documents;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class _Comment {
    @Id
    private long id;
    private _Member member;
    private String content;

    private String postId;


    @CreatedDate
    Instant createdAt = Instant.now();

    @LastModifiedDate
    Instant modifiedAt;
}
