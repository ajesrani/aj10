package com.hotel.power.management;

public abstract class Equipment {

	private boolean isON;
	
	Equipment() {
    }
	
    public boolean isON() {
        return isON;
    }

    public void switchON() {
        isON = true;
    }

    public void switchOFF() {
        isON = false;
    }
}

class Light extends Equipment {
	private int id;
	
	Light(int id) {
		this.id = id;
		
    }
	
	public String toString() {
		return " Light " + id + " : " + (isON() ? "ON" : "OFF");
	}
}

class AC extends Equipment {
	private int id;
	
	AC(int id) {
		this.id = id;
    }
	
	public String toString() {
		return " AC" + " : " + (isON() ? "ON" : "OFF");
	}
}
