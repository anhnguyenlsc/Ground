package com.ground.data.models.documents.reaction;

import com.ground.data.models.documents._Reaction;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class Comment extends _Reaction {
  /*
  links & comments
/api/comment
/api/del
/api/editusertext
/api/event_post_time
/api/follow_post
/api/hide
/api/info
/api/lock
/api/marknsfw
/api/morechildren
/api/report
/api/report_award
/api/save
/api/saved_categories
/api/sendreplies
/api/set_contest_mode
/api/set_subreddit_sticky
/api/set_suggested_sort
/api/spoiler
/api/store_visits
/api/submit
/api/unhide
/api/unlock
/api/unmarknsfw
/api/unsave
/api/unspoiler
/api/vote

   */

  @Builder.Default
  private final Type type = Type.comment;

}
