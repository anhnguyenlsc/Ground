package com.ground.services;

import com.ground.data.models.documents.MemberJoinCircle;
import com.ground.data.models.documents._Circcle;
import com.ground.data.models.documents._Member;
import com.ground.data.models.supports.Snowflake_;
import com.ground.data.models.supports.embedded.CircleEmbedded;
import com.ground.data.repositories.CategoryTypeRepository;
import com.ground.data.repositories.CircleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CircleService {
    private final ReactiveMongoTemplate mongoOps;
    private final CategoryTypeRepository catTypeRepo;
    private final CircleRepository circleRepo;
    private final CircleEmbedded circleEmbedded = new CircleEmbedded();


    public Flux<_Circcle> findAllCircles(String typeId) {
        return mongoOps.find(Query.query(Criteria.where("typeId").is(typeId)), _Circcle.class);
    }

    public Flux<_Circcle> findRootCircles(String typeCircle) {
        return mongoOps.find(Query.query(Criteria.where("typeCircle").is(typeCircle)), _Circcle.class);
    }

    public Flux<_Circcle> findOneCircle(long id) {
        return mongoOps.find(Query.query(Criteria.where("_id").is(id)), _Circcle.class);
    }

    public Mono<ResponseService> insertCircle(_Circcle circle) {
        ResponseService resObj = new ResponseService();
        Mono<_Circcle> monoCircle = circleRepo.findByName(circle.getName());
        long id = Snowflake_.generate(0);

        return monoCircle
                .flatMap(cle -> {
                    resObj.setStatus("failed");
                    resObj.setMessage("Sorry, " + circle.getName() + " is taken. Try another name.");
                    return Mono.just(resObj);
                })
                .switchIfEmpty(
                        Mono.defer(() -> {
                            if (circle.getParent() != -1L) {
                                return circleRepo.findById(Long.valueOf(String.valueOf(circle.getParent())))
                                        .flatMap(parentCircle -> {
                                            circle.setId(id);
                                            circleEmbedded.setId(id);
                                            circleEmbedded.setName(circle.getName());
                                            circleEmbedded.setAvatar(circle.getAvatar());

                                            Set<CircleEmbedded> children = parentCircle.getChildren();
                                            Set<CircleEmbedded> pyramids = parentCircle.getPyramids();
                                            if (children == null && pyramids == null) {
                                                children = new HashSet<>();
                                                pyramids = new HashSet<>();
                                            }
                                            assert children != null;
                                            children.add(circleEmbedded);
                                            pyramids.add(circleEmbedded);
                                            parentCircle.setChildren(children);
                                            parentCircle.setPyramids(pyramids);

                                            //membersCount parent Circle

                                            //membersCount children circle
                                            List<_Member> membersCount = circle.getMembersCounts();
                                            if (membersCount == null) {
                                                membersCount = new ArrayList<>();
                                            }

                                            _Member ownerMember = new _Member();
                                            ownerMember.setId(circle.getOwner().getId());
                                            membersCount.add(ownerMember);
                                            parentCircle.setMembersCounts(membersCount);

                                            // Save both the parent Circle and the circle
                                            return Mono.zip(
                                                    mongoOps.save(parentCircle),
                                                    mongoOps.save(circle)
                                            ).map(tuple -> {
                                                resObj.setStatus("success");
                                                return resObj;
                                            });
                                        });
                            }

                            else {
                                return mongoOps.save(circle)
                                        .flatMap(cir -> {
                                            return catTypeRepo.findById(circle.getTypeId())
                                                    .flatMap(targetType -> {
                                                        List<_Circcle> circleList = targetType.getCircle();
                                                        if (circleList == null) {
                                                            circleList = new ArrayList<>();
                                                        }
                                                        circleList.add(circle);
                                                        targetType.setCircle(circleList);


                                                        return mongoOps.save(targetType)
                                                                .map(savedTargetType -> {
                                                                    resObj.setStatus("success");
                                                                    resObj.setMessage("Your circle was successfully created.");
                                                                    return resObj;
                                                                });
                                                    });
                                        });
                            }
                        })
                );
    }

    //Join Circle
    public Mono<ResponseService> joinCircle(MemberJoinCircle memberJoin) {
        ResponseService resObj = new ResponseService();
        Mono<_Circcle> monoCircleId = circleRepo.findCircleById(memberJoin.getCircleId());

        return monoCircleId
                .flatMap(join -> {
                    List<_Member> joinMembersCounts = join.getMembersCounts();
                    if (joinMembersCounts == null) {
                        joinMembersCounts = new ArrayList<>();
                    }

                    _Member ownerMember = new _Member();
                    ownerMember.setId(memberJoin.getMemberId());
                    joinMembersCounts.add(ownerMember);
                    join.setMembersCounts(joinMembersCounts);
                    return mongoOps.save(join)
                            .map(joinStatus -> {
                                resObj.setStatus("success");
                                resObj.setMessage("Join circle successfully");
                                return resObj;
                            });
                });
    }

    public Flux<_Circcle> findSubCircles(long parent) {
        try {
            return mongoOps.find(Query.query(Criteria.where("parent").is(parent)), _Circcle.class);
        } catch (Exception e) {
            System.out.println(e);
            return Flux.error(e);
        }

    }
}
