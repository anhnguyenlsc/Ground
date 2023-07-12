package com.ground.data.repositories;

import com.ground.data.models.documents._Draft;
import com.ground.data.models.documents._Issue;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IssueRepository extends ReactiveMongoRepository<_Issue, Long> {
}
