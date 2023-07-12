package com.ground.data.repositories;

import com.ground.data.models.documents._Activity;
import com.ground.data.models.documents._Issue;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ActivityRepository extends ReactiveMongoRepository<_Activity, Long> {
}
