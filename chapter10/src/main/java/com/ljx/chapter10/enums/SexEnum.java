package com.ljx.chapter10.enums;

import java.util.Arrays;

//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum  SexEnum  {
//    @JsonProperty("11")
    MAIL("1","男"),
    FEMAIL("2","女");

    private String key ;
    private String value ;

    SexEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

//    @JsonValue
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

//    @JsonCreator
    public static SexEnum resolve(String key) {
        return Arrays.stream(SexEnum.values()).filter(item -> item.key.equals(key))
                .findFirst()
                .orElse(null);
    }
}
