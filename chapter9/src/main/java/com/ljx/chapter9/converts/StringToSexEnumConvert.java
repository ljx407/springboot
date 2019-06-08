package com.ljx.chapter9.converts;

import com.ljx.chapter9.enums.SexEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToSexEnumConvert implements Converter<String, SexEnum> {

    @Override
    public SexEnum convert(String s) {
        return SexEnum.getEnumByKey(s);
    }
}
