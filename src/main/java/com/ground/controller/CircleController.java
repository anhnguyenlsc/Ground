package com.ground.controller;


import com.ground.data.models.documents.MemberJoinCircle;
import com.ground.data.models.documents._Circcle;
import com.ground.data.repositories.CircleRepository;
import com.ground.services.CircleService;
import com.ground.services.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/authentication/circle")
@RequiredArgsConstructor
public class CircleController {
    private final CircleService circleService;
    private final CircleRepository circleRepo;


    //Get root circles
    @GetMapping("/rootCircles/{typeCircle}")
    public Flux<_Circcle> findRootCircles(@PathVariable("typeCircle") String typeCircle) {
        return this.circleService.findRootCircles(typeCircle);
    }

    @GetMapping("/all/{typeId}")
    public Flux<_Circcle> findCirclesByCategoryId(@PathVariable("typeId") String typeId) {
        return this.circleService.findAllCircles(typeId);
    }

    @GetMapping("/{id}")
    public Flux<_Circcle> findOneCircle(@PathVariable("id") long id) {
        return this.circleService.findOneCircle(id);
    }

    @PostMapping("/insert")
    public Mono<ResponseEntity<ResponseService>> insertCircle(@RequestBody _Circcle inputCircle) {
        return circleService.insertCircle(inputCircle)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }

    @GetMapping("/subCircles/{parent}")
    public Flux<_Circcle> findSubCircles(@PathVariable("parent") long parent) {
        return this.circleService.findSubCircles(parent);
    }

    //Join circle
    @PostMapping("/join")
    public Mono<ResponseEntity<ResponseService>> joinCircle(@RequestBody MemberJoinCircle memJoin) {
        return this.circleService.joinCircle(memJoin)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }

}
