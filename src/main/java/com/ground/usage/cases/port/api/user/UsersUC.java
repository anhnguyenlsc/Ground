package com.ground.usage.cases.port.api.user;

import com.ground.usage.values.out.UserOUT;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UsersUC {

  /**
   *   제한을 해제
   *
   */
  public Mono<UserOUT> unrestrict(String id);

  /**
   * 음소거
   */
  public Mono<UserOUT> unmute(String id);

  /**
   * 구독을 취소
   */
  public Mono<UserOUT> unfollow(String id);

  /**
   * 차단 해제
   */
  public Mono<UserOUT> unblock(String id);

  /**
   * 친구 관계 정보를 얻는다
   */
  public Mono<UserOUT> show(String id);

  /**
   * 찾기
   */
  public Mono<List<UserOUT>> search(String id, int limit);

  /**
   * 제한
   */
  public Mono<List<UserOUT>> restrict(String id, int limit);

  /**
   * 음소거
   */
  public Mono<List<UserOUT>> mute(String id, int limit);

  /**
   * following
   */
  public Mono<UserOUT> following(String id);

  /**
   * followers
   */
  public Mono<List<UserOUT>> followers(String id);

  /**
   * followers
   */
  public Mono<UserOUT> follow(String id);

  /**
   * 사용자 정보를 얻는다
   */
  public Mono<UserOUT> fetch(String id);

  /**
   * 차단
   */
  public Mono<UserOUT> block(String id);

}
