package com.laptrinhweb.view;

import com.laptrinhweb.controller.BuildingController;
import com.laptrinhweb.model.dto.BuildingDTO;

public class BuildingEditView {
    public static void main(String[] args) {
        String name = "building 4";
        String street = "Hoang hoa tham";
        String ward = null;
        String district = null;
        Integer floorArea = null;
        String [] types = new String[]{};

        BuildingDTO buildingDTO = new BuildingDTO();
        buildingDTO.setName(name);
        buildingDTO.setStreet(street);
        buildingDTO.setWard(ward);
        buildingDTO.setDistrict(district);
        buildingDTO.setFloorArea(floorArea);
        buildingDTO.setTypes(types);

        BuildingController buildingController = new BuildingController();
        buildingController.insertBuilidng(buildingDTO);

    }
}
