package com.hotel.power.management;

import java.util.List;

public class Hotel {
	
	private List<Floor> floors = null;
	
	public Hotel() {
    }
	
	public void setFloors(List<Floor> floors) {
		this.floors = floors;
	}
	
	public List<Floor> getFloors() {
		return floors;
	}
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for(Floor floor : getFloors()) {
			stringBuilder.append(floor.toString());
			stringBuilder.append("\n");
			for(MainCorridor mainCorridor : floor.getMainCorridors()) {
				stringBuilder.append(mainCorridor.toString());
				stringBuilder.append(mainCorridor.getLight().toString());
				stringBuilder.append(mainCorridor.getAC().toString());
				stringBuilder.append(System.lineSeparator());
			}
			for(SubCorridor subCorridor : floor.getSubCorridors()) {
				stringBuilder.append(subCorridor.toString());
				stringBuilder.append(subCorridor.getLight().toString());
				stringBuilder.append(subCorridor.getAC().toString());
				stringBuilder.append(System.lineSeparator());
			}
		}
		
		return stringBuilder.toString();
	}
	
	

}
