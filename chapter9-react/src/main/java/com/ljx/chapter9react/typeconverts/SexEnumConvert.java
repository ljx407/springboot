package com.ljx.chapter9react.typeconverts;

import com.ljx.chapter9react.enums.SexEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

@Convert
public class SexEnumConvert implements AttributeConverter<SexEnum,String> {

    @Override
    public String convertToDatabaseColumn(SexEnum attribute) {
        return attribute.getKey();
    }

    @Override
    public SexEnum convertToEntityAttribute(String dbData) {
        return SexEnum.getEnumByKey(dbData);
    }
}
