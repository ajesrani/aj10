package com.hotel.power.management;

import java.util.ArrayList;
import java.util.List;

/***
 * Builder Design Pattern :
 * Separates object construction of a complex object from its representation so that 
 * the same construction process can create different representation.
 */

public class HotelBuilder {
	
	private Hotel hotel;
	
	public HotelBuilder() {
		hotel = new Hotel();
	}
	
	public HotelBuilder addFloors(int floorCount) {
		List<Floor> floors = new ArrayList<Floor>();
		for (int counter = 0; counter < floorCount; counter++) {
			Floor floor = new Floor(counter+1);
			floors.add(floor);
		}
		hotel.setFloors(floors);
		return this;
	}
	
	public HotelBuilder addMainCorridors(int mainCorridorCount) {
		int floorCount = hotel.getFloors().size();
		for (int floorCounter = 0; floorCounter < floorCount; floorCounter++) {
			List<MainCorridor> mainCorridors = new ArrayList<MainCorridor>();
			for (int corridorCounter = 0; corridorCounter < mainCorridorCount; corridorCounter++) {
				MainCorridor mainCorridor = new MainCorridor(corridorCounter+1);
				mainCorridors.add(mainCorridor);
			}
			hotel.getFloors().get(floorCounter).setMainCorridors(mainCorridors);
		}
		
		return this;
	}
	
	public HotelBuilder addSubCorridors(int subCorridorCount) {
		int floorCount = hotel.getFloors().size();
		for (int floorCounter = 0; floorCounter < floorCount; floorCounter++) {
			List<SubCorridor> subCorridors = new ArrayList<SubCorridor>();
			for (int corridorCounter = 0; corridorCounter < subCorridorCount; corridorCounter++) {
				SubCorridor mainCorridor = new SubCorridor(corridorCounter+1);
				subCorridors.add(mainCorridor);
			}
			hotel.getFloors().get(floorCounter).setSubCorridors(subCorridors);
		}
		return this;
	}
	
	public Hotel build() {
		return hotel;
	}	
	
}
