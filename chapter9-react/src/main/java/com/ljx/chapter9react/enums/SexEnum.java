package com.ljx.chapter9react.enums;

import java.util.Arrays;

public enum SexEnum {
    MAIL("1","男"),
    FEMAIL("2","女");

    private String key ;
    private String value ;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    SexEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static SexEnum getEnumByKey(String key) {
        return Arrays.stream(SexEnum.values()).filter(item -> item.key.equals(key))
                .findFirst().orElse(null);
    }
}
