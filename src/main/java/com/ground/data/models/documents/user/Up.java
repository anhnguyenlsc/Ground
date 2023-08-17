package com.ground.data.models.documents.user;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * 일반 회원 가입
 * 일반 회원의 가입 신청을 합니다.
 *
 * Sign up as a regular member
 * Apply for membership as a general member.
 */

@Data
@SuperBuilder
public class Up   {
    private String userId;
    private String password;
    private String email;
    private String name;
    private String nickname;
}
