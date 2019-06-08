package com.ljx.chapter11.enums;

import java.util.Arrays;

public enum BookEnum {
    COMPUTER("1","计算机"),
    NOVAL("2","小说");

    private String key ;
    private String value ;

    BookEnum(String key, String value) {
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

    public static BookEnum resolve(String key) {
        return Arrays.stream(BookEnum.values())
                .filter(item -> item.getKey().equalsIgnoreCase(key))
                .findFirst()
                .orElse(null);
    }
}
