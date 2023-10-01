package com.ground.support;


import com.ground.data.models.documents._Circcle;
import com.ground.data.models.supports.Snowflake_;
import com.ground.data.models.supports.embedded.CircleEmbedded;
import com.ground.data.repositories.CircleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.*;

@Component
@RequiredArgsConstructor
public class RootCircles {
    private final CircleRepository circleRepo;
    private final ReactiveMongoTemplate mongoOps;
    private final List<String> circles = Arrays.asList("REALTIME", "SPORTS", "GAMING", "CELEBRITY", "CRYPTO", "BUSINESS", "MOVIE", "FASHION", "MUSIC", "LIFESTYLE");

    @PostConstruct
    public void init() {
        for (String circle : circles) {
            long id = Snowflake_.generate(0);
            Set<CircleEmbedded> children = new HashSet<>();
            Set<CircleEmbedded> pyramids = new HashSet<>();
            _Circcle newCircle = new _Circcle();
            newCircle.setId(id);
            newCircle.setName(circle);
            newCircle.setChildren(children);
            newCircle.setPyramids(pyramids);
            newCircle.setTypeCircle("root");

            // Check if the circle already exists in the database
            Query query = new Query();
            query.addCriteria(Criteria.where("name").is(circle));

            mongoOps.exists(query, _Circcle.class)
                    .flatMap(exists -> {
                        if (!exists) {
                            return mongoOps.save(newCircle);
                        } else {
                            return Mono.empty();
                        }
                    }).subscribe();
        }
    }
}
