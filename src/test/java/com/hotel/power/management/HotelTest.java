package com.hotel.power.management;

import junit.framework.TestCase;

public class HotelTest extends TestCase {
	
	private Hotel hotel;

	protected void setUp() throws Exception {
		super.setUp();
		
		HotelBuilder hBuilder = new HotelBuilder();
		hotel = hBuilder.addFloors(2).addMainCorridors(1).addSubCorridors(2).build();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testDefaultState() {
		System.out.println(hotel.toString());
		
		// Floor 1
		assertTrue("Main corridor 1: Light not switched ON", hotel.getFloors().get(0).getMainCorridors().get(0).getLight().isON());
		assertTrue("Main corridor 1: AC not switched ON", hotel.getFloors().get(0).getMainCorridors().get(0).getAC().isON());
		assertFalse("Sub corridor 1: Light switched ON", hotel.getFloors().get(0).getSubCorridors().get(0).getLight().isON());
		assertTrue("Sub corridor 1: AC not switched ON", hotel.getFloors().get(0).getSubCorridors().get(0).getAC().isON());
		assertFalse("Sub corridor 2: Light switched ON", hotel.getFloors().get(0).getSubCorridors().get(1).getLight().isON());
		assertTrue("Sub corridor 2: AC not switched ON", hotel.getFloors().get(0).getSubCorridors().get(1).getAC().isON());
	}

}
