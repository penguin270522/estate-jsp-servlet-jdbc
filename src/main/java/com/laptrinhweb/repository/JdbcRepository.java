package com.laptrinhweb.repository;

import java.util.List;

public interface JdbcRepository <T> {
    List<T> findAll();
    void insert(T t);

}
