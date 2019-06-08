//package com.ljx.chapter10.converts;
//
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.core.convert.converter.ConverterFactory;
//
//public class StringToEnumFactoryConvert implements ConverterFactory<String,Enum> {
//
//    @Override
//    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
//        return null;
//    }
//
//    public static class StringToEnumConvert<T extends Enum> implements Converter<String,T> {
//
//        private Class<T> targetType ;
//
//        public StringToEnumConvert(Class<T> targetType) {
//            this.targetType = targetType;
//        }
//
//        @Override
//        public T convert(String source) {
//            try {
//                return Enum.valueOf(targetType,source);
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
