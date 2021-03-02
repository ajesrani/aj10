package com.hotel.power.management;

import junit.framework.TestCase;

public class HotelBuilderTest extends TestCase {
	
	private HotelBuilder hBuilder;

	protected void setUp() throws Exception {
		super.setUp();
		hBuilder = new HotelBuilder();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testAddFloors() {
		Hotel hotel = hBuilder.addFloors(2).build();
		assertTrue("Incorrect floor count", hotel.getFloors().size() == 2);
	}

	public void testAddMainCorridors() {
		Hotel hotel = hBuilder.addFloors(1).addMainCorridors(1).build();
		assertTrue("Incorrect MainCorridor count", hotel.getFloors().get(0).getMainCorridors().size() == 1);
	}

	public void testAddSubCorridors() {
		Hotel hotel = hBuilder.addFloors(1).addSubCorridors(2).build();
		assertTrue("Incorrect SubCorridor count", hotel.getFloors().get(0).getSubCorridors().size() == 2);
	}

	public void testBuild() {
		Hotel hotel = hBuilder.build();
		assertNotNull("Hotel is under construction", hotel);
	}

}
