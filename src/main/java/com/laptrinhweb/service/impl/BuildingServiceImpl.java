package com.laptrinhweb.service.impl;
	
import java.util.ArrayList;
import java.util.List;

import com.laptrinhweb.constant.SystemConstant;
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
			String[] tachchuoi = buildingEntity.getType().split(",");
			StringBuilder typeBuilder = new StringBuilder();

			for(int i = 0; i < tachchuoi.length; i++){
				if(tachchuoi[i] == SystemConstant.NGUYEN_CAN){
					tachchuoi[i] = "nguyen can";
				}
				if(tachchuoi[i] == SystemConstant.NOI_THAT){
					tachchuoi[i] = "noi that";
				}
				if(tachchuoi[i] == SystemConstant.TANG_TRET){
					tachchuoi[i] = "tang tret";
				}
				typeBuilder.append(tachchuoi[i]);
				if(i < tachchuoi.length - 1){
					typeBuilder.append(", ");
				}
			}


			buildingSearchOutput.setType(typeBuilder.toString());
			resaults.add(buildingSearchOutput);
		}
		
		return resaults;
	}

}
