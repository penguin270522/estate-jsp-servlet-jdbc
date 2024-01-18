package com.laptrinhweb.dao;

import java.util.List;

import com.laptrinhweb.dao.entity.BuildingEntity;

public interface BuildingDao {
	List<BuildingEntity> findBuiling(String name, String street, String ward, String district, Integer floorArea, String type);
}
