package com.ground.services;


import com.ground.data.models.documents._Comment;
import com.ground.data.models.documents._Post;
import com.ground.data.models.supports.Snowflake_;
import com.ground.data.repositories.CommentRepository;
import com.ground.data.repositories.PostRepository;
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
public class CommentService {
    private final ReactiveMongoTemplate mongoOps;
    private final CommentRepository commentRepo;
    private final PostRepository postRepo;

    public Mono<ResponseService> insertComment(_Comment inputComment) {
        ResponseService resObj = new ResponseService();
        long id = Snowflake_.generate(0);
        inputComment.setId(id);

        return mongoOps.save(inputComment)
                .flatMap(monComment -> {
                    return postRepo.findById(Long.valueOf(inputComment.getPostId()))
                            .flatMap(targetPost -> {
                                List<_Comment> commentList = targetPost.getComment();
                                if (commentList == null) {
                                    commentList = new ArrayList<>();
                                }
                                commentList.add(inputComment);
                                targetPost.setComment(commentList);

                                return mongoOps.save(targetPost)
                                        .map(savedTargetPost -> {
                                            resObj.setStatus("success");
                                            return resObj;
                                        });
                            });
                });

    }

    public Flux<_Comment> findByPostId(long id) {
        return mongoOps.find(Query.query(Criteria.where("postId").is(id)), _Comment.class);
    }



}
