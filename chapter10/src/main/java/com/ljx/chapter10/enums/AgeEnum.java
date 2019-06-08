package com.ljx.chapter10.enums;

import java.util.Arrays;

public enum  AgeEnum {
    KIDS("1","小孩"),
    YOUNG("2","年轻人"),
    OLD("3","老年人");

    private String key ;
    private String value ;

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

    AgeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static AgeEnum resolve(String key) {
        return Arrays.stream(AgeEnum.values()).filter(item -> item.getKey().equals(key))
                .findFirst()
                .orElse(null);
    }
}
