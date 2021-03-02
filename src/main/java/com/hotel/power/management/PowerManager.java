package com.hotel.power.management;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class PowerManager implements Observer {
	
	private Hotel hotel;
	
	public PowerManager(Hotel hotel) {
		this.hotel = hotel;
	}

	public void update(Observable o, Object arg) {
		SensorData sdata = (SensorData) arg;
		toggleEquipment(sdata);
		managePowerConsumption(sdata);
	}
	
	private void managePowerConsumption(SensorData sdata) {
		Floor floor = hotel.getFloors().get(sdata.floorNumber-1);
		List<SubCorridor> subCorridors = floor.getSubCorridors();
		
		for (SubCorridor subCorridor : subCorridors) {
			int currentConsumption = floor.getCurrentPowerConsumption();
			int maxConsumption = floor.getMaxPowerConsumption();
			if (currentConsumption > maxConsumption) {
				if (subCorridor.getID() != sdata.subCorridorNumber)
					subCorridor.getAC().switchOFF();
			} else if (currentConsumption + Configuration.DEFAULT_UNITS_AC <= maxConsumption) {
				subCorridor.getAC().switchON();
			}
		}
	}

	private void toggleEquipment(SensorData sdata) {
		SubCorridor corridor = hotel.getFloors().get(sdata.floorNumber-1)
				.getSubCorridors().get(sdata.subCorridorNumber-1);
		if(sdata.state.equals("ON"))
			corridor.getLight().switchON();
		else {
			corridor.getLight().switchOFF();
			corridor.getAC().switchON();
		}
	}
}
