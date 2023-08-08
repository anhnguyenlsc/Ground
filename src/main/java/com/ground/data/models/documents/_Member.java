package com.ground.data.models.documents;


import com.ground.data.models.enums.ContactType;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document
@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class _Member {

    String user;

    String name;

    String avatar;

    String about;

    String sex;

    @Singular
    Map<ContactType, String> contacts;

    @Id
    @EqualsAndHashCode.Include
    Long id;

    String type = "MEMBER";


    public String contact(ContactType type) {
        return contacts.get(type);
    }
}
