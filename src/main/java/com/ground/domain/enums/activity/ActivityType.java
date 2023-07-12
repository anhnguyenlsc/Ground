package com.ground.domain.enums.activity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ActivityType {
  comment("댓글", "댓글을 달았습니다."),
  order("주문", "주문을 했습니다."),
  bet("배팅", "배팅을 했습니다.");

  private final String title;
  private final String description;
}
