package com.ground.data.models.documents;

import com.ground.data.models.enums.SettingStatus;
import com.ground.data.models.enums.SettingType;
import com.ground.data.models.supports.BasicDocument;
import com.ground.data.models.supports.Figure;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

//@Document
@Data
@SuperBuilder
public class _Setting extends BasicDocument<Long> implements Figure<SettingType, SettingStatus> {

//user 는 userId사용
private SettingType type;

@Getter(AccessLevel.NONE)
@Setter(AccessLevel.NONE)
private Object value;
private Class clazz;


//user 는 userId사용
private String userId;

@Singular
private List<String> keys; // join with "_"

@Singular
private List<String> names; // join with "_"

private SettingStatus status;

public String getKey() {
    return keys.stream().collect(Collectors.joining("_"));
}

public void setKey(String key) {
    keys = List.of(key.split("_"));
}

public <T> T getValue() {
    //if(value != null && value.getClass() == clazz)  return (T) value;
    //else throw new ClassCastException();
    return (T) value;
}

public void setValue(Object value) {
    this.value = value;
    this.clazz = value.getClass();
}

}

