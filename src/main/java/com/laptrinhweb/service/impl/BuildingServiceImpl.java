package com.laptrinhweb.service.impl;
	
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.laptrinhweb.constant.SystemConstant;
import com.laptrinhweb.dao.BuildingDao;
import com.laptrinhweb.dao.entity.BuildingEntity;
import com.laptrinhweb.dao.impl.BuildingDaoImpl;
import com.laptrinhweb.model.dto.BuildingDTO;
import com.laptrinhweb.model.input.BuildingSearchInput;
import com.laptrinhweb.model.output.BuildingSearchOutput;
import com.laptrinhweb.service.BuildingService;
import com.laptrinhweb.utils.BuildingTypeUtils;

public class BuildingServiceImpl implements BuildingService{

	private BuildingDao buildingDao =  new BuildingDaoImpl();
	@Override
	public List<BuildingSearchOutput> findBuilding(BuildingSearchInput buildingSearchFilterInput) {
		List<BuildingSearchOutput> resaults = new ArrayList<>();
		List<BuildingEntity> buildingEntities = buildingDao.findBuiling(buildingSearchFilterInput.getName(),
																		buildingSearchFilterInput.getStreet(),
																		buildingSearchFilterInput.getWard(),
																		buildingSearchFilterInput.getDistrict(),
																		buildingSearchFilterInput.getFloorArea(),
																		buildingSearchFilterInput.getType()	);

		for (BuildingEntity buildingEntity : buildingEntities) {
			BuildingSearchOutput buildingSearchOutput = new BuildingSearchOutput();
			buildingSearchOutput.setId(buildingEntity.getId());
			buildingSearchOutput.setName(buildingEntity.getName());
			buildingSearchOutput.setStreet(buildingEntity.getStreet() + " - " + buildingEntity.getWard() + " - " + buildingEntity.getDistrict());
			buildingSearchOutput.setFloorArea(buildingEntity.getFloorArea());
			buildingSearchOutput.setType(getType(buildingEntity.getType()));
			resaults.add(buildingSearchOutput);

		}
		
		return resaults;
	}

	@Override
	public void insertBuildingDTO(BuildingDTO buildingDTO) {
		BuildingEntity buildingEntity = new BuildingEntity();
		buildingEntity.setName(buildingDTO.getName());
		buildingEntity.setStreet(buildingDTO.getStreet());
		buildingDao.insertBuilding(buildingEntity, buildingDTO.getRentArea());
	}

	private String getType(String type){
		List<String> results = new ArrayList<>();
		Map<String , String > buildingTypes = BuildingTypeUtils.getTypes();
		for (String item : type.split(",")){
			String typeName = buildingTypes.get(item);
			results.add(typeName);
		}
		return String.join(",", results);
	}


}
