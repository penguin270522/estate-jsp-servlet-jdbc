package com.laptrinhweb.repository.impl;

import com.laptrinhweb.repository.CustomerRepository;
import com.laptrinhweb.repository.entity.CustomerEntity;

import java.util.List;
import java.util.Objects;

public class CustomerRepositoryImpl extends SimpleJdbcRepository<CustomerEntity> implements CustomerRepository <CustomerEntity>{
    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void insert(Objects objects) {

    }
}
