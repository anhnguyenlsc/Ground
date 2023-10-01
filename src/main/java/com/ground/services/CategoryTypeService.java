package com.ground.services;


import com.ground.data.models.documents._CategoryType;
import com.ground.data.models.documents._Circcle;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class CategoryTypeService {
    private final ReactiveMongoTemplate template;

    public Flux<_CategoryType> findByCatId(String id) {
        return template.find(Query.query(Criteria.where("categoryId").is(id)), _CategoryType.class);
    }

    public Flux<_Circcle> findAllCircle(String id) {
        return template.find(Query.query(Criteria.where("typeId").is(id)), _Circcle.class);
    }

    public Flux<_Circcle> findOneCircle(long id) {
        return template.find(Query.query(Criteria.where("_id").is(id)), _Circcle.class);
    }
}
