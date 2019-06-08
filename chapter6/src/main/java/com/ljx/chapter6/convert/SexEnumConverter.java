package com.ljx.chapter6.convert;

import com.ljx.chapter6.enums.SexEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SexEnumConverter implements Converter<String, SexEnum> {

    @Override
    public SexEnum convert(String source) {
        return SexEnum.getByKey(Integer.valueOf(source));
    }
}
