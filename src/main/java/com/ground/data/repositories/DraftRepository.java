package com.ground.data.repositories;

import com.ground.data.models.documents._Circle;
import com.ground.data.models.documents._Draft;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DraftRepository extends ReactiveMongoRepository<_Draft, Long> {
}
