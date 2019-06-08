package com.ljx.chapter7.enums;

import java.util.Arrays;

public enum  SexEnum {
    MALE("1","男"),
    FEMALE("2","女")
    ;
    private String key ;
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

    public static SexEnum getSexEnumByKey(String key) {
        return Arrays.stream(SexEnum.values()).filter(item -> item.key.equals(key)).findFirst().orElse(null);
    }
}
