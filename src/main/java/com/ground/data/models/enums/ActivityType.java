package com.ground.data.models.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Deprecated
@Getter
@RequiredArgsConstructor
public enum ActivityType {



  deposit(Group.commercial,"입금",false,"입금"),
  withdraw(Group.commercial,"출금",false,"출금"),
  bet(Group.gamble,"베팅",false,"베팅"),
  revise(Group.gamble,"금액 조정",false,"금액 조정"),
  bonus(Group.gamble,"보너스",false,"보너스"),
  realize(Group.gamble,"파트너 보상",false,"파트너 보상"),

  r(Group.reward,"파트너 보상",true,"파트너 보상");

  private final Group group;
  private final String title;
  private final boolean exposure;
  private final String description;

  @RequiredArgsConstructor
  public static enum Group {

    gamble("도박"),
    commercial("상업적"),
    reward("보상"),

    non_commercial("비 상업적");

    private final String description;

  }

}
