package com.ground.data.repositories;

import com.ground.data.models.documents._Post;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PostRepository extends ReactiveMongoRepository<_Post, Long> {
    Mono<List<_Post>> findByMemberId(Long id);

    Mono<List<_Post>> findByMemberIdOrderByCreatedAtDesc(Long id);
}
