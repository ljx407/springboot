package com.ljx.chapter3_1.conditional;

import com.ljx.chapter3_1.annotion.MyProfile;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Component
public class MyProfileCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        if(!metadata.isAnnotated(MyProfile.class.getName())) {
            return true ;
        }
        MultiValueMap<String, Object> allAnnotationAttributes = metadata.getAllAnnotationAttributes(MyProfile.class.getName());
        List<Object> values = allAnnotationAttributes.get("value");

        return values.stream().anyMatch(item -> context.getEnvironment().getProperty("myprofile.active", "-1").equals(((String[]) item)[0]));

//        for (Object item : values) {
//            String property = context.getEnvironment().getProperty("myprofile.active", "-1");
//            if(property.equals(String.valueOf(item))) {
//                return true ;
//            }
//        }
//        return false ;

    }
}
