package com.ljx.chapter5.converter;

import com.ljx.chapter5.enums.SexEnum;

import javax.persistence.AttributeConverter;

public class SexEnumConvert implements AttributeConverter<SexEnum,Integer> {

    @Override
    public Integer convertToDatabaseColumn(SexEnum attribute) {
        return attribute.getKey();
    }

    @Override
    public SexEnum convertToEntityAttribute(Integer dbData) {
        return SexEnum.getByKey(dbData);
    }
}
