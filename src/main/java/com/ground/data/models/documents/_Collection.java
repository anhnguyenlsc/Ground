package com.ground.data.models.documents;


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
import java.util.List;

@Document(collection = "_Collection")
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class _Collection {
  /*
  collections
/api/v1/collections/add_post_to_collection
/api/v1/collections/collection
/api/v1/collections/create_collection
/api/v1/collections/delete_collection
/api/v1/collections/follow_collection
/api/v1/collections/remove_post_in_collection
/api/v1/collections/reorder_collection
/api/v1/collections/subreddit_collections
/api/v1/collections/update_collection_description
/api/v1/collections/update_collection_display_layout
/api/v1/collections/update_collection_title
   */
    @Id
    private long id;
    String title;
    List<_Post> savedPosts = new ArrayList<>();
    private long memberId;

    @CreatedDate
    Instant createdAt = Instant.now();

    @LastModifiedDate
    Instant modifiedAt;
}
