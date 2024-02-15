package com.laptrinhweb.repository.entity;

import com.laptrinhweb.annotation.Column;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RentAreaEntity extends BaseEntity  {

    @Column(name = "value")
    private Integer value;
    @Column(name = "buildingid")
    private Long buildingId;
    @Column(name = "createddate")
    private LocalDateTime createdDate;
    @Column(name ="modifieddate")
    private LocalDateTime modifiedDate;
    @Column(name ="createdby")
    private String createdBy;
    @Column(name ="modifiedby")
    private String modifiedBy;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
