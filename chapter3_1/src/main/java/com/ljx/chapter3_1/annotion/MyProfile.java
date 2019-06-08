package com.ljx.chapter3_1.annotion;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyProfile {
    String[] value();
}
