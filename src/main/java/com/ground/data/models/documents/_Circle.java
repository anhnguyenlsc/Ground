package com.ground.data.models.documents;

import com.ground.data.models.supports.Variant;
import com.ground.domain.enums.ContactProperty;
import com.ground.domain.enums.circle.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

/**
 * 상업적 또는 비상업적 모임 단위(group,colony,community)
 */
@Document
@SuperBuilder
@Data
public class _Circle extends Variant<String, CircleType, CircleStatus, CircleProperty> {

  //서클이름
  private String name;
  private String description;

  private ObjectId avatar;
  private ObjectId banner;
  private ObjectId qrCode;

  //서클의 소유자
  @DBRef
  private _User owner;

  //서클의 관리자
  @DBRef
  private List<_User> moderators;

  //계통, 계열( circle id list)
  private List<String> lineage;
  //상위 서클로의 확산 여부
  //Whether it spreads to the upper circles
  private boolean propagation;
  private Policy policy;

  //위치 정보 - 위치기반 서비스에 활용
  private GeoJsonPoint location;
  // multi_location_enabled

  //서클의 유형
  private List<String> tags;

  //서클의 도메인
  private List<Domain> domains;

  private final Map<CircleSettingType,Object> settings = new HashMap<>();

  //서클의 접점
  private Map<ContactProperty, Object> contacts;

  private Map<MetaProperty, Object> meta;

  @DBRef(lazy = true)
  private List<_User> banned;

  @DBRef(lazy = true)
  private List<_User> muted;

  //이익 공유 여부
  @Deprecated (forRemoval = false) private boolean profitSharing;
  @Deprecated (forRemoval = false) private Map<String,Double> profitSharingRatio;

  @Deprecated (forRemoval = false)
  private Map<String , Object> finances;
  @Deprecated (forRemoval = false)
  private Map<String , Object> asset;
  @Deprecated (forRemoval = false)
  private Map<String , Object> credit;


  public enum Policy {


  }


  public enum MetaProperty {

  }


  @Getter
  @Builder
  public static class Domain {
    private String name;
    private String description;
  }


  public class Setting {

    public void of(final CircleSettingType type, final Object value) {
      settings.put(type, value);
    }

    /*
    @Builder
    public Setting(SettingType type, Object value) throws ClassNotFoundException {
      Assert.notNull(type, "SettingType must not be null");
      var _class = Class.forName(type.getType());
      Assert.isTrue(_class.isInstance(value), "value must be instance of " + type.getType());

      this.key = type;
      this.value = value;
    }
     */

    private void throwIfInvalidType(final String expectedType) {

    }
  }

}


  /*
  subreddits
/about/banned
/about/contributors
/about/moderators
/about/muted
/about/wikibanned
/about/wikicontributors
/about/where
/api/delete_sr_banner
/api/delete_sr_header
/api/delete_sr_icon
/api/delete_sr_img
/api/recommend/sr/srnames
/api/search_reddit_names
/api/search_subreddits
/api/site_admin
/api/submit_text
/api/subreddit_autocomplete
/api/subreddit_autocomplete_v2
/api/subreddit_stylesheet
/api/subscribe
/api/upload_sr_img
/api/v1/subreddit/post_requirements
/r/subreddit/about
/r/subreddit/about/edit
/r/subreddit/about/rules
/r/subreddit/about/traffic
/sidebar
/sticky
/subreddits/default
/subreddits/gold
/subreddits/mine/contributor
/subreddits/mine/moderator
/subreddits/mine/streams
/subreddits/mine/subscriber
/subreddits/mine/where
/subreddits/new
/subreddits/popular
/subreddits/search
/subreddits/where
/users/new
/users/popular
/users/search
/users/where

   */