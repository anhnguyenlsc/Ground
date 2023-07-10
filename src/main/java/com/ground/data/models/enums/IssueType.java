package com.ground.data.models.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Deprecated
@Getter
@RequiredArgsConstructor
public enum IssueType {




  deposit("입금"),
  withdraw("출금"),
  bet("베팅"),
  revise("금액 조정"),
  bonus("보너스"),
  realize("파트너 보상");

  private final String description;

}
