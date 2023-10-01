package com.ground.data.models.documents;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "_CategoryType")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class _CategoryType {
    @Id
    private String id;

    private String title;
    private String categoryId;

    List<_Circcle> circle = new ArrayList<>();
}
