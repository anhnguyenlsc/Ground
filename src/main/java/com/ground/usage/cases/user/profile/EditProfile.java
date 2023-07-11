package com.ground.usage.cases.user.profile;


import com.ground.usage.values.base.Out;
import com.ground.usage.values.in.EditableProfileIN;
import com.ground.usage.values.out.UserOUT;
import reactor.core.publisher.Mono;

/**
 * 프로필을 수정하는 유스케이스
 */
public interface EditProfile {

  /**
   * 프로필 조회(본인)
   * <p>
   * view_your_profile_yourself
   */
  public Mono<Out<UserOUT>> viewProfile();

  /**
   * 프로필 조회(타인)
   * <p>
   * view_other_people's_profile
   */
  public Mono<Out<UserOUT>> viewProfile(String id);


  /**
   * 수정할수 있는 프로필
   * @param id
   * @return
   */

  public Mono<Out<UserOUT>> editProfile(EditableProfileIN editableProfile);


}
