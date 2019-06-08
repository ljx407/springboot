package com.ljx.chapter10.converts;

import com.ljx.chapter10.enums.SexEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SexEnumConvert implements Converter<String, SexEnum> {
    @Override
    public SexEnum convert(String source) {

        return SexEnum.resolve(source);
    }
}
