package com.ground.data.transfer.supports;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;


@Data
public class BasicTransfer<I extends Serializable> {


  private I id;

  private String createdBy;

  private Instant createdDate;

  private String lastModifiedBy;

  private Instant lastModifiedDate;


}
