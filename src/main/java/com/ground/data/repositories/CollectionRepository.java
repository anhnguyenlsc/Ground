package com.ground.data.repositories;

import com.ground.data.models.documents._Collection;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CollectionRepository extends ReactiveMongoRepository<_Collection, Long> {
    Mono<_Collection> findByMemberId(Long memberId);

    Mono<_Collection> findCollectionByTitle(String title);
}
