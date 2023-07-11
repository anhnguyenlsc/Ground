package com.ground.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CircleService {

  private final ReactiveMongoTemplate MongoOps;



}
