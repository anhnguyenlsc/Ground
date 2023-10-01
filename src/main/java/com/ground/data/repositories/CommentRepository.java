package com.ground.data.repositories;

import com.ground.data.models.documents._Comment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CommentRepository extends ReactiveMongoRepository<_Comment, Long> {
}
