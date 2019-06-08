package com.ljx.chapter10.jpaconvert;

import com.ljx.chapter10.enums.SexEnum;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;

@Component
public class SexEnumJapConvert implements AttributeConverter<SexEnum,String> {

    @Override
    public String convertToDatabaseColumn(SexEnum attribute) {
        return attribute.getKey();
    }

    @Override
    public SexEnum convertToEntityAttribute(String dbData) {
        return SexEnum.resolve(dbData);
    }
}
