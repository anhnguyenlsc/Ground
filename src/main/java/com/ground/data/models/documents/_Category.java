package com.ground.data.models.documents;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "_Category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class _Category {
    @Id
    private String id;
    private String title;
    private String icon;
}
