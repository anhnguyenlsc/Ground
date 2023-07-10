package com.ground.data.models.supports.embedded;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.Map;

@Data
@Builder
public class Account {

  private float over;
  private float deposit;
  private float withdraw;
  private float bet;
  private float win;
  private float fail; // fail commission
  private float cancel;
  private float refund;
  private float swapin;
  private float swapout;
  private float adjustin;
  private float adjustout;
  private float balance;


  private float commission;











  private int depositCount;
  private int withdrawCount;


  private int betCount;
  private int winCount;
  private int failCount; // fail count
  private int cancelCount;
  private int refundCount;

  private int bonus;
  private Instant carryOverAt;

}
