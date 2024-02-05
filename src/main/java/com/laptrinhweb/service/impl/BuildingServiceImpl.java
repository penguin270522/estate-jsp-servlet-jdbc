package com.laptrinhweb.service.impl;
	
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.laptrinhweb.constant.SystemConstant;

import com.laptrinhweb.enums.BuildingTypeEnums;
import com.laptrinhweb.model.dto.BuildingDTO;
import com.laptrinhweb.model.input.BuildingSearchInput;
import com.laptrinhweb.model.output.BuildingSearchOutput;
import com.laptrinhweb.service.BuildingService;
import com.laptrinhweb.utils.BuildingTypeUtils;
import com.laptrinhweb.repository.BuildingRepository;
import com.laptrinhweb.repository.entity.BuildingEntity;
import com.laptrinhweb.repository.impl.BuildingRepositoryImpl;

public class BuildingServiceImpl implements BuildingService{

	private BuildingRepository buildingDao =  new BuildingRepositoryImpl();
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
			buildingSearchOutput.setType(getType2(buildingEntity.getType()));
			System.out.println("test 0: " + buildingEntity.getType());
			System.out.println("test : " + getType2(buildingEntity.getType()));
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

	@Override
	public List<BuildingDTO> findAll() {
		List<BuildingEntity> buildingEntities = buildingDao.findAll();
		return null;
	}

	private String getType1(String type){
		List<String> results = new ArrayList<>();
		Map<String , String > buildingTypes = BuildingTypeUtils.getTypes();
		for (String item : type.split(",")){
			String typeName = buildingTypes.get(item);
			results.add(typeName);
		}
		return String.join(",", results);
	}

	private String getType2(String type){
		List<String> results = new ArrayList<>();
		String[] typeSplit = type.split(",");
		for(String item : typeSplit){
			switch (item){
				case SystemConstant.NGUYEN_CAN:
					results.add(SystemConstant.NGUYEN_CAN_CODE);
					break;
				case SystemConstant.NOI_THAT:
					results.add(SystemConstant.NOI_THAT_CODE);
					break;
				case SystemConstant.TANG_TRET:
					results.add(SystemConstant.TANG_TRET_CODE);
					break;
			}
		}
		return String.join(",", results);
	}

	private String getTypeVersion3(String type){
		String[] typeSplit = type.split(",");
		List<String> results = new ArrayList<>();
		for(String item : typeSplit){
			results.add(BuildingTypeEnums.valueOf(item).getValue());
		}

		return String.join(",", results);
	}

}
