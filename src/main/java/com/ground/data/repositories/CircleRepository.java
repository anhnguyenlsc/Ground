package com.ground.data.repositories;

import com.ground.data.models.documents._Circcle;
import com.ground.data.models.documents._Circle;
import com.ground.data.models.documents._User;
import com.ground.data.models.documents._Usser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

public interface CircleRepository extends ReactiveMongoRepository<_Circcle, Long> {
    Mono<_Circcle> findByName(String name);
    Mono<_Circcle> findCircleById(Long id);
}
