package com.ground.services;


import com.ground.data.models.documents._Collection;
import com.ground.data.models.documents._Post;
import com.ground.data.models.supports.Snowflake_;
import com.ground.data.repositories.CollectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectionService {
    private final ReactiveMongoTemplate mongoOps;
    private final CollectionRepository collectionRepo;
//    private final _Collection collection = new _Collection();

    public Flux<_Collection> findAllCollectionPosts(long memberId) {
        return mongoOps.find(Query.query(Criteria.where("memberId").is(memberId)), _Collection.class);
    }

    public Mono<ResponseService> addNewPostToCollection(_Post savedPost, Long memberId) {
        ResponseService resObj = new ResponseService();
//        Mono<_Collection> monoCollection = collectionRepo.findCollectionByTitle(collection.getTitle());
        Mono<_Collection> monColMemId = collectionRepo.findByMemberId(memberId);

        return monColMemId
                .flatMap(coll -> {
                    List<_Post> savedPostList = coll.getSavedPosts();

                    if (savedPostList == null) {
                        savedPostList = new ArrayList<>();
                    }

                    savedPostList.add(savedPost);
                    coll.setSavedPosts(savedPostList);

                    return mongoOps.save(coll)
                            .map(savedPosts -> {
                                resObj.setStatus("success");
                                resObj.setMessage("Post is added to collection");
                                return resObj;
                            });
                })
                .switchIfEmpty(
                        Mono.defer(() -> {
                            _Collection collection = new _Collection();
                            long id = Snowflake_.generate(0);
                            collection.setId(id);
                            collection.setTitle("Saved Posts");
                            collection.setMemberId(memberId);
                            List<_Post> savedPostList = new ArrayList<>();
                            savedPostList.add(savedPost);
                            collection.setSavedPosts(savedPostList);

                            return mongoOps.save(collection)
                                    .map(savedPosts -> {
                                        resObj.setStatus("success");
                                        resObj.setMessage("New collection created and post added");
                                        return resObj;
                                    });
                        })
                );

    }
}
