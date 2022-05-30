package com.shlee7131.financedemo.infra.repo;

import java.util.List;

public interface DBRepository<T> {
    <R> T findById(R id);
    <R> List<T> findAll(R id);
    <R> T findByKeywords(R r);
    T save(T t);
    <R> void deleteById(R id);
}
