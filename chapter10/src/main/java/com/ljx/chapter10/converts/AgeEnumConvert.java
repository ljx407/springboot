package com.ljx.chapter10.converts;

import com.ljx.chapter10.enums.AgeEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AgeEnumConvert implements Converter<String, AgeEnum> {
    @Override
    public AgeEnum convert(String source) {

        return AgeEnum.resolve(source);
    }
}
