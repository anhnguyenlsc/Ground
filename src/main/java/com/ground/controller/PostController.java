package com.ground.controller;

import com.ground.data.models.documents.SharePost;
import com.ground.data.models.documents._Comment;
import com.ground.data.models.documents._Post;
import com.ground.data.repositories.PostRepository;
import com.ground.services.CommentService;
import com.ground.services.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/authentication/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostRepository postRepo;
    private final CommentService commentService;

    @GetMapping("/getPost/{id}")
    public Flux<_Post> getPostInfo(@PathVariable("id") long id) {
        return this.postService.findAllByPostId(id);
    }

    @PostMapping("/insert")
    public Mono<ResponseEntity<ResponseService>> insertPost(@RequestBody _Post inputPost) {
        return postService.insertPost(inputPost)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }

    @GetMapping("/{circleId}")
    public Flux<_Post> findByCircleId(@PathVariable("circleId") long id) {
        return this.postService.findByCircleId(id);
    }

    @PostMapping("/comment/insertcomment")
    public Mono<ResponseEntity<ResponseService>> insertComment(@RequestBody _Comment inputComment) {
        return commentService.insertComment(inputComment)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }

    @GetMapping("/comment/getComments/{postId}")
    public Flux<_Comment> findByPostId(@PathVariable("postId") long id) {
        return this.commentService.findByPostId(id);
    }

    @PostMapping("/like")
    public Mono<ResponseEntity<ResponseService>> likePost(@RequestParam long postId, @RequestParam String memberId) {
        return postService.updatePostByLikes(postId, memberId)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }

    @PostMapping("/share")
    public Mono<ResponseEntity<ResponseService>> sharePost(@RequestBody SharePost sharePost) {
        return postService.updatePostByShare(sharePost.getPost(), sharePost.getOwner(), sharePost.getShareContent(), sharePost.getCircleId())
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }

}
