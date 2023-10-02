package com.laptrinhweb.view;

import java.util.List;
import java.util.Scanner;

import com.laptrinhweb.controller.BuildingController;
import com.laptrinhweb.model.input.BuildingSearchInput;
import com.laptrinhweb.model.output.BuildingSearchOutput;

public class BuildingListView {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("nhap ten toa nha: ");
		String name = input.nextLine();
		
		//model;
		BuildingSearchInput buildingSearchInput = new BuildingSearchInput();
		buildingSearchInput.setName(name);
		
		//controller
		BuildingController buildingController = new BuildingController();
		List<BuildingSearchOutput> buildingSearchOutput = buildingController.findBuilding(buildingSearchInput);
		
		for (BuildingSearchOutput buildingSearchOutput2 : buildingSearchOutput) {
			System.out.print("ID: " + buildingSearchOutput2.getId());
			
			System.out.print(", Name: " + buildingSearchOutput2.getName());
			
			System.out.println(", Street: " + buildingSearchOutput2.getStreet());
			
		}
		input.close();
	}
}
