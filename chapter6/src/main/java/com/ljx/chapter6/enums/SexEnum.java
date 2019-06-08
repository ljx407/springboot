package com.ljx.chapter6.enums;

import java.util.Arrays;

public enum SexEnum {
    MALE(1,"男"),
    FEMALE(2,"女");

    private Integer key ;
    private String value ;

    SexEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static SexEnum getByKey(Integer key) {
        return Arrays.stream(SexEnum.values()).filter(item -> item.key == key)
                .findFirst().orElse(null);
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
