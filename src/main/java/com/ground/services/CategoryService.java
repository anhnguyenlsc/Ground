package com.ground.services;


import com.ground.data.models.documents._Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final ReactiveMongoTemplate template;

    public Flux<_Category> findCats() {
        return template.findAll(_Category.class);
    }

    public Mono<_Category> findOne(String id) {
        return template.findOne(Query.query(Criteria.where("_id").is(id)), _Category.class);
    }
}
