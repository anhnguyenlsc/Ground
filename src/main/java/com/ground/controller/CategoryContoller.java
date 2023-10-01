package com.ground.controller;

import com.ground.data.models.documents._Category;
import com.ground.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("api/authentication/category")
@RequiredArgsConstructor
public class CategoryContoller {
    private final CategoryService catService;

    @GetMapping
    public Flux<_Category> findCats() {
        return this.catService.findCats();
    }

    @GetMapping("/{id}")
    public Mono<_Category> findCat(@PathVariable("id") String id) {
        return this.catService.findOne(id);
    }

}
