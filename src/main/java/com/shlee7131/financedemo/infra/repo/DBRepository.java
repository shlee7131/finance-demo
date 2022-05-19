package com.shlee7131.financedemo.infra.repo;

public interface DBRepository<T> {
    T findById();
}
