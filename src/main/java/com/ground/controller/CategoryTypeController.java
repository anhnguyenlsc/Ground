package com.ground.controller;


import com.ground.data.models.documents._CategoryType;
import com.ground.data.models.documents._Circcle;
import com.ground.services.CategoryTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/authentication/categoryType")
@RequiredArgsConstructor
public class CategoryTypeController {
    private final CategoryTypeService service;

    @GetMapping("/{categoryId}")
    public Flux<_CategoryType> findByCategoryId(@PathVariable("categoryId") String id) {
        return this.service.findByCatId(id);
    }

    @GetMapping("/{categoryId}/{typeId}")
    public Flux<_Circcle> findCircles(@PathVariable("typeId") String typeId) {
        return this.service.findAllCircle(typeId);
    }

    @GetMapping("/{categoryId}/{typeId}/{id}")
    public Flux<_Circcle> findOneCircle(@PathVariable("id") long _id) {
        return this.service.findOneCircle(_id);
    }
}
