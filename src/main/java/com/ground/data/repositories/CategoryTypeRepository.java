package com.ground.data.repositories;

import com.ground.data.models.documents._CategoryType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CategoryTypeRepository extends ReactiveMongoRepository<_CategoryType, String> {
    Mono<_CategoryType> findById(String id);
}
