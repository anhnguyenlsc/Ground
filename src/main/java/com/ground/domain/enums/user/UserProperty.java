package com.ground.domain.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public enum UserProperty {

  basic("기본", "기본정보"),
  basic_picture("사진", "프로필 사진을 설정합니다", "org.bson.types.ObjectId",Visibility.PUBLIC),
  basic_name("이름", "이름을 설정합니다", "java.lang.String",Visibility.PROTECTED),
  basic_birthday("생일", "생일을 설정합니다", "java.time.LocalDate",Visibility.PROTECTED),
  basic_gender("성별","","java.lang.String",Visibility.PROTECTED,Map.of("m", "남성", "w", "여성","n", "선택안함")),

  contact("연락처", "연락처 정보"),
  contact_email("이메일", "이메일을 설정합니다", "java.lang.String",Visibility.PROTECTED),
  contact_phone("전화번호", "전화번호를 설정합니다", "java.lang.String",Visibility.PROTECTED),
  //contact_kakao("카카오톡", "카카오톡 아이디를 설정합니다", "java.lang.String"),
  //contact_facebook("페이스북", "페이스북 아이디를 설정합니다", "java.lang.String"),
  //contact_instagram("인스타그램", "인스타그램 아이디를 설정합니다", "java.lang.String"),
  //contact_twitter("트위터", "트위터 아이디를 설정합니다", "java.lang.String"),

  addresses("주소", "주소 정보"),
  addresses_home("집","집 주소 정보", "java.lang.String",Visibility.PROTECTED),
  addresses_work("직장", "직장 주소 정보", "java.lang.String",Visibility.PROTECTED),
  addresses_other("기타", "기타 주소 정보", "java.lang.String",Visibility.PROTECTED),

  locale("지역", "서클의 지역을 설정합니다"),
  locale_timezone("시간대", "서클의 시간대를 설정합니다", "java.util.TimeZone",Visibility.PROTECTED, Map.of()),
  locale_language("언어", "서클의 언어를 설정합니다", "java.util.Locale",Visibility.PROTECTED, Map.of()),
  locale_currency("통화", "서클의 통화를 설정합니다", "java.util.Currency",Visibility.PROTECTED, Map.of()),
  ;

  private final String title;
  private final String description;
  private String type;
  private Visibility visibility;
  private Map<Object, String> options;

  UserProperty(String title, String description, String type,Visibility visibility) {
    this.title = title;
    this.description = description;
    this.type = type;
  }

  public static enum Visibility {
    PRIVATE , PROTECTED, PUBLIC
  }
}
