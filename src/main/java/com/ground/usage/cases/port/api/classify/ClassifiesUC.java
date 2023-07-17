package com.ground.usage.cases.port.api.classify;

import reactor.core.publisher.Mono;

public interface ClassifiesUC {

/*
api/feeds/recommended	권장 사항 목록을 가져옵니다/ 매개변수에서 이전 응답의 선택적 paging_token을 전달하십시오/
api/feeds/notificationseen	모든 알림을 지웁니다/ api/feeds/notifications() 후에 이 작업을 수행하고 보이지 않는 항목이 있는지 new_stories를 확인하는 것이 좋습니다/
api/feeds/notifications	권장 사항 목록을 가져옵니다/ 매개 변수에서 이전 응답의 max_id 및 pagination_first_record_timestamp와 함께 선택적 필터 유형 및 선택적 페이지 매김 개체를 전달합니다/
api/feeds/fetchThreads	매개변수에서 스레드를 가져오려는 사용자의 사용자 ID와 이전 응답의 next_max_id의 선택적 max_id를 전달하십시오/
api/feeds/fetchReplies	매개변수에서 응답을 받으려는 사용자의 사용자 ID와 이전 응답의 next_max_id의 선택적 max_id를 전달하십시오/
api/feeds/fetch	기본 피드를 가져옵니다/ 매개변수에서 이전 응답의 next_max_id의 선택적 max_id를 전달합니다/
 */

  /**
   * 권장 사항 목록을 가져옵니다
   * @param pagingToken
   * @return
   */
  public Mono<String> recommended(String pagingToken);

  /**
   * 모든 알림을 지웁니다
   * @return
   */
  public Mono<String> notificationseen();

  /**
   * 권장 사항 목록을 가져옵니다
   * @param maxId
   * @param paginationFirstRecordTimestamp
   * @param filterType
   * @param pagingToken
   * @return
   */
  public Mono<String> notifications(String maxId, String paginationFirstRecordTimestamp, String filterType, String pagingToken);

  /**
   * 스레드를 가져옵니다
   * @param userId
   * @param maxId
   * @param pagingToken
   * @return
   */
  public Mono<String> fetchThreads(String userId, String maxId, String pagingToken);

  /**
   * 응답을 받으려는 사용자의 사용자 ID와 이전 응답의 next_max_id의 선택적 max_id를 전달하십시오
   * @param userId
   * @param maxId
   * @param pagingToken
   * @return
   */
  public Mono<String> fetchReplies(String userId, String maxId, String pagingToken);

  /**
   * 기본 피드를 가져옵니다
   * @param maxId
   * @param pagingToken
   * @return
   */
  public Mono<String> fetch(String maxId, String pagingToken);

}
