package com.ground.data.models.enums;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
public enum ContactType {
    url("홈 페이지"), email("Email"), telegram("텔레그램"), phone("휴대폰"), kakao("카카오 아이디"), line("라인 아이디");

    final String label;
}
