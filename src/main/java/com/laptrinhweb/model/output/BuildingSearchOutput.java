package com.laptrinhweb.model.output;

public class BuildingSearchOutput {
	private Long id;
	private String name;
	private String street;
	private Integer floorArea;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public Integer getFloorArea() { return floorArea; }
	public void setFloorArea(Integer floorArea) { this.floorArea = floorArea; }
}
