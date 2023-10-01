package com.ground.data.models.documents;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCollection {
    _Post savedPosts;
    Long memberId;
}
