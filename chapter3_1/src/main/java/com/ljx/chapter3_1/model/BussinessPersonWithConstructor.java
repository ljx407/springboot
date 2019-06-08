package com.ljx.chapter3_1.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BussinessPersonWithConstructor implements Person {

    private Animal animal ;

    public BussinessPersonWithConstructor(@Autowired @Qualifier("dog") Animal animal) {
        this.animal = animal ;
    }

    @Override
    public void service() {
        animal.use();
    }
}
