package com.ljx.chapter3_1.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BussinessPerson implements Person {

    @Autowired
    @Qualifier("cat")
    private Animal animal;

    @Override
    public void service() {
        animal.use();
    }
}
