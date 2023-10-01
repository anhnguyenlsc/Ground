package com.ground.data.models.documents;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberJoinCircle {
    private long memberId;
    private long circleId;
}
