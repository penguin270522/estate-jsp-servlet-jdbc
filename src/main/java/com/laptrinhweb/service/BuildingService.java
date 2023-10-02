
package com.laptrinhweb.service;

import java.util.List;

import com.laptrinhweb.model.input.BuildingSearchInput;
import com.laptrinhweb.model.output.BuildingSearchOutput;

public interface BuildingService {
	List<BuildingSearchOutput> findBuilding(BuildingSearchInput buildingSearchFilterInput);

}
