package com.ljx.chapter7cache.enums;

import java.util.Arrays;

public enum SexEnum {
    MALE("1","男"),
    FEMALE("2", "女");

    private String key ;
    private String value ;
    SexEnum(String key,String value) {
        this.key = key;
        this.value = value ;
    }

    public String getKey() {
        return this.key;
    }

    public static SexEnum getEnumByKey(String key) {
        return Arrays.stream(SexEnum.values()).filter(item -> item.getKey().equals(key))
                .findFirst().orElse(null);
    }


}
