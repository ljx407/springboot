package com.ljx.chapter5.enums;

import java.util.Arrays;

public enum  SexEnum {
    FEMALE(1,"女"),
    MAIL(2,"男");

    private int key ;
    private String value ;

    SexEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static SexEnum getByKey(int key) {
        return Arrays.stream(SexEnum.values())
                .filter(item -> item.key == key)
                .findFirst()
                .orElse(null);
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
