package com.hotel.power.management;

import junit.framework.TestCase;

public class MotionDetectorTest extends TestCase {
	
	private MotionDetector mDetector;
	private Hotel hotel;

	protected void setUp() throws Exception {
		super.setUp();
		
		HotelBuilder hBuilder = new HotelBuilder();
		hotel = hBuilder.addFloors(2).addMainCorridors(1).addSubCorridors(2).build();
		PowerManager audit = new PowerManager(hotel);
		mDetector = new MotionDetector(audit);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testEventOccurred() throws InterruptedException {
		SensorData sData = new SensorData();
		
		// Movement in Floor 1, Sub corridor 2
		sData.floorNumber=1 ; sData.subCorridorNumber=2 ; sData.state = "ON";
		mDetector.eventOccurred(sData);
		assertTrue("Light not switched ON", hotel.getFloors().get(0).getSubCorridors().get(1).getLight().isON());
		
		Thread.sleep(6000);
		
		// No movement in Floor 1, Sub corridor 2 for a minute
		sData.floorNumber=1 ; sData.subCorridorNumber=2 ; sData.state = "OFF";
		mDetector.eventOccurred(sData);
		assertFalse("Light still not switched OFF", hotel.getFloors().get(0).getSubCorridors().get(1).getLight().isON());
	}

}
