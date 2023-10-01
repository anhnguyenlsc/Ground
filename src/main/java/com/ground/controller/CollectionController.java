package com.ground.controller;


import com.ground.data.models.documents.PostCollection;
import com.ground.data.models.documents._Collection;
import com.ground.data.models.documents._Post;
import com.ground.services.CollectionService;
import com.ground.services.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/authentication/collections")
@RequiredArgsConstructor
public class CollectionController {
    private final CollectionService collectionService;
    @GetMapping("/all/{memberId}")
    public Flux<_Collection> getAllPosts(@PathVariable("memberId") long memberId) {
        return this.collectionService.findAllCollectionPosts(memberId);
    }

    @PostMapping("/add_post_to_collection")
    public Mono<ResponseEntity<ResponseService>> addPostsToCollection(@RequestBody PostCollection postCollection) {
        return collectionService.addNewPostToCollection(postCollection.getSavedPosts(), postCollection.getMemberId())
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }
}
