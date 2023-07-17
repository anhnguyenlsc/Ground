package com.ground.usage.cases.port.api.issue;

import reactor.core.publisher.Mono;

public interface IssuesUC {
  /*
api/posts/reply	이 메서드는 스레드에 대한 응답을 생성하는 데 사용됩니다/ 첫 번째 매개변수로 답장 내용, 두 번째로 사용자 ID, 세 번째로 게시글 ID를 전달합니다/
api/posts/quote	이 메서드는 견적 이슈를 만드는 데 사용됩니다/ 인용문의 텍스트를 첫 번째 매개변수로, 두 번째로 사용자 ID, 세 번째로 게시물 ID를 전달합니다/
api/posts/likers	매개변수에 좋아하는 게시물의 ID를 전달합니다/
api/posts/like	이 메서드는 이슈를 좋아하는 데 사용됩니다/ 첫 번째 매개변수로 게시물 ID를 전달하고 두 번째 매개변수로 사용자 ID를 전달합니다/
api/posts/fetch	매개 변수에서 정보를 얻으려는 게시물의 ID와 이전 요청의 선택적 페이지 매김 토큰을 전달합니다/
api/posts/delete	이 메서드는 이슈를 삭제하는 데 사용됩니다/ 첫 번째 매개변수로 게시물 ID를 전달하고 두 번째 매개변수로 사용자 ID를 전달합니다/
api/posts/create	이 메서드는 이슈를 만드는 데 사용됩니다/ 스레드의 텍스트를 첫 번째 매개변수로 전달하고 사용자 ID를 두 번째 매개변수로 전달합니다/
   */

  /**
   * 이 메서드는 이슈를 다시 게시하지 않는 데 사용됩니다
   * @param id
   * @return
   */
  public Mono<String> unrepost(String id);

  /**
   * 이 메서드는 이슈를 달리하는 데 사용됩니다 ?
   * @param id
   * @return
   */
  public Mono<String> unlike(String id);

  /**
   * 이슈를 다시 게시
   */
  public Mono<String> repost(Long issueId);

  /**
   * 이슈에 대한 응답을 생성
   */
  public Mono<String> reply(String text,Long issueId);
  public Mono<String> quote(String id);
  public Mono<String> likers(String id);
  public Mono<String> like(String id);
  public Mono<String> fetch(String id);
  public Mono<String> delete(String id);
  public Mono<String> create(String id);


}
