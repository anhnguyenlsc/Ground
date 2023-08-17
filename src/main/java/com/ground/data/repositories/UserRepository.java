package com.ground.data.repositories;

import com.ground.data.models.documents._User;
import com.ground.data.models.documents._Usser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.Optional;


public interface UserRepository extends ReactiveMongoRepository<_Usser, String> {
    Mono<_Usser> findByEmail(String email);
}
