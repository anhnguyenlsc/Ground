package com.ground.data.models.documents;

import com.ground.data.models.supports.Variant;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Map;


@Data
@SuperBuilder
public class _User extends Variant<String, _User.Type, _User.Status, _User.Property> implements UserDetails {
  /**
   * UserDetails
   * @return
   */
  private String password;
  private Collection<Authority> authorities;
  private boolean enabled = true;
  private boolean accountNonExpired = true;
  private boolean accountNonLocked = true;
  private boolean credentialsNonExpired = true;

  /**
   * User
   */
  //자신의 노드이름
  private String node;
  //부모 노드들 이름
  private List<String> paths;
  private Type type;
  private Status status;


  private Map<Property, Object> properties;
  private Map<String, Object> meta;


  @Override
  public String getUsername() {
    return getId();
  }

  public enum Type {

  }

  public enum Status {

  }


  public enum Property {

    name, phone, address, birthday, nickname, email, password, type, status, node, paths,a

  }

  public enum Authority implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
      return name();
    }
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


   */


}
