package com.ground.data.models.documents;

import com.ground.data.models.supports.Count;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class _Post {
    @Id
    private long id;
    private _Member member;

    private long circleId;
    private String circleName;

    private String title;
    private String content;

    private List<String> image = new ArrayList<>();
    private List<String> tags = new ArrayList<>();

    List<String> likes = new ArrayList<>();

    List<String> dislikes = new ArrayList<>();

    List<_Member> share = new ArrayList<>();

    private String shareContent;
    private _Post from;

    List<_Comment> comment = new ArrayList<>();

    @CreatedDate
    Instant createdAt = Instant.now();

    @LastModifiedDate
    Instant modifiedAt;
}
