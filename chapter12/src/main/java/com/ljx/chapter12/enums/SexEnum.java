package com.ljx.chapter12.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum SexEnum {
    MAIL("1","男"),
    FEMAIL("2","女");

    private String key ;
    @JsonValue
    private String value ;

    SexEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @JsonCreator
    public static SexEnum resolve(String key) {
        return Arrays.stream(SexEnum.values()).filter(item -> item.getKey().equals(key))
                .findFirst()
                .orElse(null);
    }
}
