package com.shlee7131.financedemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Type;


public class TestConfigContainer {
    private AnnotationConfigApplicationContext container;
    private static TestConfigContainer instance = new TestConfigContainer();

    public <T> T getBean(Class<T> type){
        return (T) container.getBean(type);
    }

    private TestConfigContainer(){
        this.container = new AnnotationConfigApplicationContext(TestConfig.class);
    };

    public static TestConfigContainer getInstance(){
        return instance;
    }
}

