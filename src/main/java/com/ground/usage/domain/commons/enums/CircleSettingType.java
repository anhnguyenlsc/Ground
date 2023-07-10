package com.ground.usage.domain.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public enum CircleSettingType {

  policy("정책", "서클의 운영 정책을 설정 합니다"),
  policy_access("공개", "서클을 사용자에게 공개하는 정책"),
  policy_access_approval("가입 승인", "가입할때 승인이 필요한지 여부를 설정합니다", "java.lang.Boolean", Map.of("true", "승인필요", "false", "자동승인")),
  policy_access_issue("이슈 생성", "사용자가 이슈를 생성할수 있는지 여부를 설정 합니다", "java.lang.Boolean", Map.of("true", "가능", "false", "불가능")),

  alerts("알림", "서클의 알림을 설정합니다"),
  alerts_issue("새소식", "서클의 알림을 설정합니다", "java.lang.Boolean", Map.of("true", "알림", "false", "알림안함")),
  alerts_reaction("반응", "자신의 글에 대한 반응을 알릴지 설정합니다.", "java.lang.Boolean", Map.of("true", "알림", "false", "알림안함")),

  locale("지역", "서클의 지역을 설정합니다"),
  locale_timezone("시간대", "서클의 시간대를 설정합니다", "java.util.TimeZone", Map.of()),
  locale_language("언어", "서클의 언어를 설정합니다", "java.util.Locale", Map.of()),
  locale_currency("통화", "서클의 통화를 설정합니다", "java.util.Currency", Map.of()),
  ;

  private final String title;
  private final String description;
  private String type;
  private Map<String, String> options;

  CircleSettingType(String title, String description, String type) {
    this.title = title;
    this.description = description;
    this.type = type;
  }
}
