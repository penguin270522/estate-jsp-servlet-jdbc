package com.laptrinhweb.service.impl;
	
import java.util.ArrayList;
import java.util.List;

import com.laptrinhweb.dao.BuildingDao;
import com.laptrinhweb.dao.entity.BuildingEntity;
import com.laptrinhweb.dao.impl.BuildingDaoImpl;
import com.laptrinhweb.model.input.BuildingSearchInput;
import com.laptrinhweb.model.output.BuildingSearchOutput;
import com.laptrinhweb.service.BuildingService;

public class BuildingServiceImpl implements BuildingService{

	private BuildingDao buildingDao =  new BuildingDaoImpl();
	@Override
	public List<BuildingSearchOutput> findBuilding(BuildingSearchInput buildingSearchFilterInput) {
		List<BuildingSearchOutput> resaults = new ArrayList<>();
		List<BuildingEntity> buildingEntities = buildingDao.findBuiling(buildingSearchFilterInput.getName());
		for (BuildingEntity buildingEntity : buildingEntities) {
			BuildingSearchOutput buildingSearchOutput = new BuildingSearchOutput();
			buildingSearchOutput.setId(buildingEntity.getId());
			buildingSearchOutput.setName(buildingEntity.getName());
			buildingSearchOutput.setStreet(buildingEntity.getStreet() + " - " + buildingEntity.getWard() + " - " + buildingEntity.getDistrict());
			resaults.add(buildingSearchOutput);
		}
		return resaults;
	}

}
