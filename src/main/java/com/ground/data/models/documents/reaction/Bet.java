package com.ground.data.models.documents.reaction;

import com.ground.data.models.documents._Reaction;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Bet extends _Reaction {

  @Builder.Default
  private final Type type = Type.bet;

}
