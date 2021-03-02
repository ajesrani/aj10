package com.hotel.power.management;

import java.util.List;

public class Floor {

	private int id;

    private List<MainCorridor> mainCorridors = null;

    private List<SubCorridor> subCorridors = null;
    
    public Floor(int id) {
        this.id = id;
    } 
    
    public void setMainCorridors(List<MainCorridor> mainCorridors) {
		this.mainCorridors = mainCorridors;
	}
    
    public void setSubCorridors(List<SubCorridor> subCorridors) {
		this.subCorridors = subCorridors;
	}
    
	public List<MainCorridor> getMainCorridors() {
		return mainCorridors;
	}
	
	public List<SubCorridor> getSubCorridors() {
		return subCorridors;
	}
	
	public int getCurrentPowerConsumption() {

        int result = 0;

        for (MainCorridor mainCorridor : mainCorridors)
            result += mainCorridor.getCurrentPowerConsumption();

        for (SubCorridor subCorridor : subCorridors)
            result += subCorridor.getCurrentPowerConsumption();

        return result;
    }
	
	public int getMaxPowerConsumption() {
        return (mainCorridors.size() * 15) + (subCorridors.size() * 10);
    }

	@Override
	public String toString() {
		return "\t\tFloor " + id;
	}
    
    
    
}
