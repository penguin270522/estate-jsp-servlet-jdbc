package com.laptrinhweb.enums;

public enum BuildingTypeEnums {
    tang_tret("tang tret"),
    nguyen_can("nguyen can"),
    noi_that("noi that");

    private String value;
    private BuildingTypeEnums (String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
