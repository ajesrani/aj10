package com.hotel.power.management;

import junit.framework.TestCase;

public class PowerManagerTest extends TestCase {
	
	private PowerManager audit;
	private Hotel hotel;

	protected void setUp() throws Exception {
		super.setUp();
		
		HotelBuilder hBuilder = new HotelBuilder();
		hotel = hBuilder.addFloors(2).addMainCorridors(1).addSubCorridors(2).build();
		audit = new PowerManager(hotel);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testPowerManager() {
		assertNotNull("Hotel is under construction", hotel);
	}

	public void testUpdate() throws InterruptedException {
		SensorData sData = new SensorData();
		
		// Movement in Floor 1, Sub corridor 2
		sData.floorNumber=1 ; sData.subCorridorNumber=2 ; sData.state = "ON";
		audit.update(null, sData);
		assertTrue("Light not switched ON", hotel.getFloors().get(0).getSubCorridors().get(1).getLight().isON());
		assertFalse("AC not switched OFF", hotel.getFloors().get(0).getSubCorridors().get(0).getAC().isON());
		
		Thread.sleep(6000);
		
		// No movement in Floor 1, Sub corridor 2 for a minute
		sData.floorNumber=1 ; sData.subCorridorNumber=2 ; sData.state = "OFF";
		audit.update(null, sData);
		assertFalse("Light still not switched OFF", hotel.getFloors().get(0).getSubCorridors().get(1).getLight().isON());
		assertTrue("AC not switched ON", hotel.getFloors().get(0).getSubCorridors().get(0).getAC().isON());
	}

}
