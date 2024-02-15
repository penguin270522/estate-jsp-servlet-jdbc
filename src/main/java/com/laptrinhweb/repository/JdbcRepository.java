package com.laptrinhweb.repository;

import java.util.List;
import java.util.Objects;

public interface JdbcRepository <T> {
    List<T> findAll();
    void insert(Objects objects);
    List<T> findByCondition(String sql);

    T findById(Long id);
}
