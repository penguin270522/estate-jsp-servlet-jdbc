package com.laptrinhweb.view;

import java.util.List;
import java.util.Scanner;

import com.laptrinhweb.controller.BuildingController;
import com.laptrinhweb.model.input.BuildingSearchInput;
import com.laptrinhweb.model.output.BuildingSearchOutput;

public class BuildingListView {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
//		System.out.println("nhap ten toa nha: ");
//		String name = input.nextLine();
		String name = "nam bum";
		Integer floorArea = null;
		String street = null;
		String ward = null;
		String district = null;
		//model;
		BuildingSearchInput buildingSearchInput = new BuildingSearchInput();
		buildingSearchInput.setName(name);
		buildingSearchInput.setFloorArea(floorArea);
		buildingSearchInput.setStreet(street);
		buildingSearchInput.setWard(ward);
		buildingSearchInput.setDistrict(district);
		
		//controller
		BuildingController buildingController = new BuildingController();
		List<BuildingSearchOutput> buildingSearchOutput = buildingController.findBuilding(buildingSearchInput);
		
		for (BuildingSearchOutput buildingSearchOutput2 : buildingSearchOutput) {
			System.out.print("ID: " + buildingSearchOutput2.getId());
			
			System.out.print(", Name: " + buildingSearchOutput2.getName());
			
			System.out.print(", Street: " + buildingSearchOutput2.getStreet());

			System.out.println(", FloorArea: " + buildingSearchOutput2.getFloorArea());

			System.out.println(", Type: " + buildingSearchOutput2.getType());
			
		}


	}
}
