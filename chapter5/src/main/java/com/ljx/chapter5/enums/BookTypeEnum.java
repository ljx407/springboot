package com.ljx.chapter5.enums;

import java.util.Arrays;

public enum  BookTypeEnum {
    NEW(1,"新书"),
    OLD(2,"老书")
    ;

    private Integer key ;
    private String name ;

    BookTypeEnum(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

    public static BookTypeEnum getByKey(Integer key) {
        return Arrays.stream(BookTypeEnum.values())
                .filter(item -> item.getKey() == key)
                .findFirst()
                .orElse(null);
    }

    public Integer getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

}
