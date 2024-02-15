package com.laptrinhweb.repository.entity;

import com.laptrinhweb.annotation.Column;

public class BaseEntity {
    @Column(name = "id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
