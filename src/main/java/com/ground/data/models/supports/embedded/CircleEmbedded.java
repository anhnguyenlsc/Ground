package com.ground.data.models.supports.embedded;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CircleEmbedded {
    long id;
    String name;
    String avatar;
}
