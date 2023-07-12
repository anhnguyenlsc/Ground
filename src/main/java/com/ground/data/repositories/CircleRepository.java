package com.ground.data.repositories;

import com.ground.data.models.documents._Circle;
import com.ground.data.models.documents._User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CircleRepository extends ReactiveMongoRepository<_Circle, Long> {
}
