package com.laptrinhweb.controller;

import java.util.List;

import com.laptrinhweb.model.dto.BuildingDTO;
import com.laptrinhweb.model.input.BuildingSearchInput;
import com.laptrinhweb.model.output.BuildingSearchOutput;
import com.laptrinhweb.service.BuildingService;
import com.laptrinhweb.service.impl.BuildingServiceImpl;

public class BuildingController {
	private BuildingService buildingService = new BuildingServiceImpl();
	
	public List<BuildingSearchOutput> findBuilding(BuildingSearchInput buildingSearchInput){
		List<BuildingSearchOutput> resaults = buildingService.findBuilding(buildingSearchInput);
		return resaults;
		
	}

	public void insertBuilidng(BuildingDTO buildingDTO){
		buildingService.insertBuildingDTO(buildingDTO);
	}

}
