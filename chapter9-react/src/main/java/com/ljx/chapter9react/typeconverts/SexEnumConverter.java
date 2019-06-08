package com.ljx.chapter9react.typeconverts;

import com.ljx.chapter9react.enums.SexEnum;
import org.springframework.core.convert.converter.Converter;

public class SexEnumConverter implements Converter<String, SexEnum> {

    @Override
    public SexEnum convert(String source) {
        return SexEnum.getEnumByKey(source);
    }
}
