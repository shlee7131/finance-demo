package com.shlee7131.financedemo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

@Component
public class Transformer {
    public <T,R> R transform(T t, Class<R> type) {
        R object = null;
        try {
            object = type.getConstructor().newInstance();
            BeanUtils.copyProperties(t, object);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return object;
    }
}
