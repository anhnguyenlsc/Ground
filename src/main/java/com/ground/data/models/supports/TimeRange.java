package com.ground.data.models.supports;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;

@Data
@RequiredArgsConstructor(staticName = "of")
public class TimeRange implements Range<LocalTime, LocalTime> {


  private final LocalTime from;
  private final LocalTime to;


}
