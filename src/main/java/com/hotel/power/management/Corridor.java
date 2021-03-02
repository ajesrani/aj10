package com.hotel.power.management;

public abstract class Corridor {

	private int id;

	private Light light;

	private AC ac;

	public Corridor(int id) {
		this.id = id;
		light = new Light(id);
		ac = new AC(id);
	}

	public int getCurrentPowerConsumption() {

		int result = 0;

		result += light.isON() ? Configuration.DEFAULT_UNITS_LIGHTS : 0;
		result += ac.isON() ? Configuration.DEFAULT_UNITS_AC : 0;

		return result;
	}

	public Light getLight() {
		return light;
	}

	public AC getAC() {
		return ac;
	}
}

class MainCorridor extends Corridor {

	private int id;

	public MainCorridor(int id) {
		super(id);
		this.id = id;
		getLight().switchON();
		getAC().switchON();
	}

	public int getID() {
		return id;
	}

	@Override
	public String toString() {
		return "Main corridor " + id;
	}
}

class SubCorridor extends Corridor {

	private int id;

	public SubCorridor(int id) {
		super(id);
		this.id = id;
		getLight().switchOFF();
		getAC().switchON();
	}

	public int getID() {
		return id;
	}

	@Override
	public String toString() {
		return "Sub Corridor " + id;
	}
}
