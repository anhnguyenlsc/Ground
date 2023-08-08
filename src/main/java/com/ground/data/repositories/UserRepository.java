package com.ground.data.repositories;

import com.ground.data.models.documents._User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface UserRepository extends ReactiveMongoRepository<_User, String> {
}
