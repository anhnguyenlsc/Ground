package com.ground.services;


import com.ground.data.models.documents._Circcle;
import com.ground.data.models.documents._Member;
import com.ground.data.models.documents._Post;
import com.ground.data.models.supports.Snowflake_;
import com.ground.data.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final ReactiveMongoTemplate mongoOps;
    private final PostRepository postRepo;
    public Flux<_Post> findAllByPostId(long id) {
        return mongoOps.find(Query.query(Criteria.where("id").is(id)), _Post.class);
    }

    public Mono<ResponseService> insertPost(_Post inputPost) {
        ResponseService resObj = new ResponseService();
        long id = Snowflake_.generate(0);
        inputPost.setId(id);
        return mongoOps.save(inputPost)
                .map(post -> {
                    resObj.setStatus("success");
                    resObj.setMessage("Post created successfully");
                    resObj.setPayload(post);
                    return resObj;
                });
    }

    public Flux<_Post> findByCircleId(long id) {
        return mongoOps.find(Query.query(Criteria.where("circleId").is(id)), _Post.class);
    }

    public Mono<ResponseService> updatePostByLikes(long postId, String memberId) {
        ResponseService resObj = new ResponseService();
        return mongoOps.findById(postId, _Post.class)
                .flatMap(post -> {
                    // Add the member ID to the list of likes
                    post.getLikes().add(memberId);
                    // Save the updated post
                    return mongoOps.save(post);
                })
                .map(savedPost -> {
                    resObj.setStatus("success");
                    resObj.setMessage("Post updated successfully");
                    return resObj;
                });
    }

    public Mono<ResponseService> updatePostByShare(_Post post, _Member member, String shareContent, long circleId) {
        ResponseService resObj = new ResponseService();
        long id = Snowflake_.generate(0);

        return mongoOps.findById(post.getId(), _Post.class)
                .flatMap(sharedPost -> {
                    List<_Member> shareList = sharedPost.getShare();
                    if (shareList == null) {
                        shareList = new ArrayList<>();
                    }
                    //save members who shared posts
                    shareList.add(member);
                    sharedPost.setShare(shareList);

                    // Create a new shared post
                    _Post newSharedPost = new _Post();
                    newSharedPost.setId(id);
                    newSharedPost.setCircleId(circleId);
                    newSharedPost.setMember(member);
                    newSharedPost.setShareContent(shareContent);
                    newSharedPost.setFrom(post);

                    return Mono.zip(
                            mongoOps.save(sharedPost),
                            mongoOps.save(newSharedPost));
                })
                .map(sp -> {
                    resObj.setStatus("success");
                    resObj.setMessage("Shared post successfully");

                    return resObj;
                });
    }

}
