package com.ground.data.models.enums;

import com.ground.data.models.supports.AmountRange;
import com.ground.data.models.supports.RateAndMax;
import com.ground.data.models.supports.TimeRange;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 설정 테이블의 키값을 정의한다.
 * key tree path 구조, user tree path 구조 두가지의 조합으로 설정값을 관리한다.
 * ( 정확도를 위해 실시간 머지 사용 / 상위 level 값 변경시 하위 level 값은 삭제된다)
 * 설정값의 변경및 조회 권한을 관리해야 한다. ( 하위 레벨만 가능, 수정 불가능, 조회만 가능 등..)
 * 사용자 path 경로와 키 경로가 머지되어 설정 값이 적용되어 진다.(ex: root 유저의 설정값은 하위 레벨의 사용자 설정 값이 없으면 user level까지 전파된다..)
 */


@RequiredArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true,prefix = "_")
@Getter
public enum SettingKey {

  //top level
  root(null,"root","root", SettingKey.class),

  //first level
  info(root,"info","info", SettingKey.class),
  health(root,"health","health", SettingKey.class),
  basis(root,"기본정보","기본정보 설정", SettingKey.class),
  security(root,"security","security", SettingKey.class),
  user(root,"user","user", SettingKey.class),
  deposit(root,"deposit","deposit", SettingKey.class),
  withdraw(root,"withdraw","withdraw", SettingKey.class),
  point(root,"point","point", SettingKey.class),
  gmae(root,"gmae","gmae", SettingKey.class),
  draft(root,"draft","draft", SettingKey.class),

  //info second level
  iTitle(info,"title","title",String.class),
  iLogo(info,"logo","logo",String.class),
  iDescription(info,"description","description",String.class),
  iTags(info,"tags","tags",String.class),
  iContact(info,"contact","contact",String.class),

  //health second level
  hStatus(health,"health_status","health_status",Boolean.class),
  hDeposit(health,"health_deposit","health_deposit",Boolean.class),
  hWithdraw(health,"health_withdraw","health_withdraw",Boolean.class),
  hBetting(health,"health_betting","health_betting", Map.class),

  //basis second level
  bSites(basis,"sites","sites", Set.class),
  bSounds(basis,"sounds","sounds", Map.class),

  //security second level
  sAllowAccessSameIp(security,"동일 IP 접근 허용","동일 IP로 여러 사용자가 접근 가능한가?",Boolean.class),
  sAlertAccessSameIP(security,"동일 IP 접근 알림","동일 IP로 여러 사용자가 접근 할 경우 관리자에 알림",Boolean.class),

  //user second level
  uJoin(user,"회원가입","회원가입이 가능한가?",Boolean.class),
  uWelcome(user,"환영메시지","회원가입시 화면에 표시되는 환영 메시지",String.class),
  uJoinMessage(user,"가입환영 쪽지","가입환영 쪽지 메시지",String.class),
  uJoinSms(user,"가입환영 SMS","가입환영 SMS 메시지",String.class),
  uBlockMessage(user,"차단/탈퇴 회원","차단/탈퇴 회원이 접근시 메시지",String.class),
  uInquiryTerm(user,"재문의 시간차","얼마나 자주 문의가 가능한가?",Integer.class),

  //deposit second level
  dUptime(deposit,"입금시간","입금가능 시간 (from ~ to) ", TimeRange.class),
  dRange(deposit,"입금가능 범위","입금가능 범위(최소 ~ 최대)", AmountRange.class),
  dFirstBouns(deposit,"첫충 보너스","비율/최대", RateAndMax.class),//RateAndMax
  dBouns(deposit,"매충 보너스","비율/최대",RateAndMax.class),
  dAmounts(deposit,"입금버튼","입금 버튼 리스트", List.class),
  dQuickAccountButton(deposit,"빠른입금 계좌","빠른입금 계좌버튼을 표시",Boolean.class),
  dAutoSendAccountInfo(deposit,"계좌자동제공","계좌 정보를 자동으로 제공합니다",Boolean.class),
  dGuide(deposit,"입금안내","입금안내",String.class),


  ;

  private final SettingKey _parent;
  private final String _title;
  private final String _description;
  private final Class<?> _dataClass;
  private Object initValue;


  public EnumSet<SettingKey> children(){


    return EnumSet.allOf(SettingKey.class).stream()
        .filter(settingKey -> settingKey.parent() == this)
        .collect(Collectors.toCollection(() -> EnumSet.noneOf(SettingKey.class)));
  }
}
