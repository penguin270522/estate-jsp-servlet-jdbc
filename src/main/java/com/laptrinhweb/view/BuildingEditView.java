package com.laptrinhweb.view;

import com.laptrinhweb.controller.BuildingController;
import com.laptrinhweb.model.dto.BuildingDTO;

public class BuildingEditView {
    public static void main(String[] args) {
        String name = "building 6";
        String street = "50 ham nghi";
        String ward = null;
        String district = null;
        Integer floorArea = null;
        String [] types = new String[]{};
        Integer[] rentArea = {100};
        BuildingDTO buildingDTO = new BuildingDTO();
        buildingDTO.setRentArea(rentArea);
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
