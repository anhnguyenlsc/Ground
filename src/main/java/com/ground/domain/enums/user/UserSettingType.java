package com.ground.domain.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public enum UserSettingType {

  basic("기본", "기본정보"),
  basic_picture("사진", "프로필 사진을 설정합니다", "org.bson.types.ObjectId"),
  basic_name("이름", "이름을 설정합니다", "java.lang.String"),
  basic_birthday("생일", "생일을 설정합니다", "java.time.LocalDate"),

  policy("정책", "서클의 운영 정책을 설정 합니다"),
  policy_access("공개", "서클을 사용자에게 공개하는 정책"),
  policy_access_approval("가입 승인", "가입할때 승인이 필요한지 여부를 설정합니다", "java.lang.Boolean", Map.of("true", "승인필요", "false", "자동승인")),
  policy_access_issue("이슈 생성", "사용자가 이슈를 생성할수 있는지 여부를 설정 합니다", "java.lang.Boolean", Map.of("true", "가능", "false", "불가능")),

  alerts("알림", "서클의 알림을 설정합니다"),
  alerts_issue("새소식", "서클의 알림을 설정합니다", "java.lang.Boolean", Map.of("true", "알림", "false", "알림안함")),
  alerts_reaction("반응", "자신의 글에 대한 반응을 알릴지 설정합니다.", "java.lang.Boolean", Map.of("true", "알림", "false", "알림안함")),

  payments("결제", "서클의 결제정보를 설정합니다"),
  payments_methods("결제수단", "서클의 결제수단을 설정합니다", "java.lang.String", Map.of("card", "카드", "bank", "계좌")),
  payments_card("카드", "서클의 카드정보를 설정합니다", "java.lang.String"),

  /**
   * 컨텐츠, 서비스와 제품이 있고
   * 베팅, 구독과 구매가 있다.
   */
  service("서비스", "서클의 서비스정보를 설정합니다"),
  service_subscriptions("구독", "서클의 구독정보를 설정합니다"),
  ;

  private final String title;
  private final String description;
  private String type;
  private Map<String, String> options;

  UserSettingType(String title, String description, String type) {
    this.title = title;
    this.description = description;
    this.type = type;
  }
}
