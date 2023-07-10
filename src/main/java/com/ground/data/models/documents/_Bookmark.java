package com.ground.data.models.documents;

import com.ground.data.models.supports.BasicDocument;
import lombok.Data;
import lombok.experimental.SuperBuilder;

//@Document
@Data
@SuperBuilder
public class _Bookmark extends BasicDocument<Long> {

private String userId;
private Long ref;
private String description;

}

