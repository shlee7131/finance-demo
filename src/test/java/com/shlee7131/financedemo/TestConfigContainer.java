package com.shlee7131.financedemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Type;


public class TestConfigContainer {
    public AnnotationConfigApplicationContext container =
            new AnnotationConfigApplicationContext(TestConfig.class);

    public <T> T getBean(Class<T> type){
        return (T) container.getBean(type);
    }
}

