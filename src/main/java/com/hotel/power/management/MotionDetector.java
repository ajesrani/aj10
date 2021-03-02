package com.hotel.power.management;

import java.util.Observable;

public class MotionDetector extends Observable {
	
	public MotionDetector(PowerManager audit) {
		addObserver(audit);
		//deleteObserver(audit);
		//deleteObservers();
	}
	
	public void eventOccurred(SensorData sdata) {
		setChanged();
		notifyObservers(sdata);
	}
}
