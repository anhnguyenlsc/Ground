package com.ground.data.models.documents.reaction;

import com.ground.data.models.documents._Activity;
import com.ground.domain.enums.activity.ActivityType;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Bet extends _Activity {

  @Builder.Default
  private final ActivityType type = ActivityType.bet;

}
