package com.ground.data.models.documents;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SharePost {
    private _Post post;
    private _Member owner;
    private String shareContent;
    private long circleId;
}
