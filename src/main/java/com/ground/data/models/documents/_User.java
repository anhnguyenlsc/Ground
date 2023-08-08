package com.ground.data.models.documents;

import com.ground.data.models.supports.Variant;
import com.ground.domain.enums.authentication.UserAuthority;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Map;


@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Accessors(chain = true, prefix = "_")
public class _User extends Variant<String, _User.Type, _User.Status, _User.Property> implements UserDetails {
  /**
   * UserDetails
   */
  private String _password;//authentication
  private Collection<UserAuthority> _authorities;//authorization
  private boolean _enabled = true;
  private boolean _accountNonExpired = true;
  private boolean _accountNonLocked = true;
  private boolean _credentialsNonExpired = true;

  //private Type _type;
  //private Status _status;
  //private Map<Property, Object> _properties;

  private Map<String, Object> _meta;

  @DBRef
  private List<_Circle> _circles;



  @Override
  public String getUsername() {
    return getId();
  }

  public enum Type {

  }

  /**
   * 1. 회원가입
   * 2. 이메일 또는 휴대폰 인증
   * 3. 탈퇴
   */
  public enum Status {
    _join, _verified, _deleted
  }

  public enum Property {

    name, phone, address, birthday, nickname, email, password, type, status, node, paths,a

  }


  /*
  account
/api/v1/me
/api/v1/me/blocked
/api/v1/me/friends
/api/v1/me/karma
/api/v1/me/prefs
/api/v1/me/trophies
/prefs/blocked
/prefs/friends
/prefs/messaging
/prefs/trusted
/prefs/where


users
/api/block_user
/api/friend
/api/report_user
/api/setpermissions
/api/unfriend
/api/user_data_by_account_ids
/api/username_available
/api/v1/me/friends/username
/api/v1/user/username/trophies
/user/username/about
/user/username/comments
/user/username/downvoted
/user/username/gilded
/user/username/hidden
/user/username/overview
/user/username/saved
/user/username/submitted
/user/username/upvoted
/user/username/where



  "user": {
                "is_private": false,
                "profile_pic_url": "https://scontent.cdninstagram.com/v/t51.2885-19/357376107_1330597350674698_8884059223384672080_n.jpg?stp=dst-jpg_s150x150&_nc_ht=scontent.cdninstagram.com&_nc_cat=1&_nc_ohc=euIj8dtTGIkAX-mW2_l&edm=APs17CUBAAAA&ccb=7-5&oh=00_AfAUZzobOIH6imLnb2Z3iXoWY5H1Fv_kNnyG8T4UGgJegQ&oe=64AED800&_nc_sid=10d13b",
                "username": "zuck",
                "hd_profile_pic_versions": [
                    {
                        "height": 320,
                        "url": "https://scontent.cdninstagram.com/v/t51.2885-19/357376107_1330597350674698_8884059223384672080_n.jpg?stp=dst-jpg_s320x320&_nc_ht=scontent.cdninstagram.com&_nc_cat=1&_nc_ohc=euIj8dtTGIkAX-mW2_l&edm=APs17CUBAAAA&ccb=7-5&oh=00_AfD5z6UgnQH54dihPnMrXgH2L-mLCMGlFsIF9Ug7U4RWdA&oe=64AED800&_nc_sid=10d13b",
                        "width": 320
                    },
                    {
                        "height": 640,
                        "url": "https://scontent.cdninstagram.com/v/t51.2885-19/357376107_1330597350674698_8884059223384672080_n.jpg?stp=dst-jpg_s640x640&_nc_ht=scontent.cdninstagram.com&_nc_cat=1&_nc_ohc=euIj8dtTGIkAX-mW2_l&edm=APs17CUBAAAA&ccb=7-5&oh=00_AfD4BaVu4cDcX53xPocD-3o_ZbKIESxUZhlU08FBpycCsA&oe=64AED800&_nc_sid=10d13b",
                        "width": 640
                    }
                ],
                "is_verified": true,
                "biography": "",
                "biography_with_entities": null,
                "follower_count": 2663947,
                "profile_context_facepile_users": null,
                "bio_links": [
                    {
                        "url": ""
                    }
                ],
                "pk": "314216",
                "full_name": "Mark Zuckerberg",
                "id": null
            }
        }
   */



}
