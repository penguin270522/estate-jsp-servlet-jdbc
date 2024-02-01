package com.laptrinhweb.repository;


import com.laptrinhweb.repository.entity.BuildingEntity;

import java.util.List;

public interface BuildingRepository extends JdbcRepository<BuildingEntity>{
    List<BuildingEntity> findBuiling(String name, String street, String ward, String district, Integer floorArea, String type);
    void insertBuilding(BuildingEntity buildingEntity, Integer [] rentAreas);

    public BuildingEntity findById(Long id);
}
