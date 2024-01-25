package com.laptrinhweb.repository.entity;

public class RentAreaEntity extends BaseEntity  {


    private Integer value;
    private Long buildingId;



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
}
