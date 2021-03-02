package com.hotel.power.management;

import junit.framework.TestCase;

public class ParserTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testParseUserInput() {
		//InputData idata = Parser.getUserInput();
		InputData idata = new InputData(2, 1, 2);
		assertTrue("Incorrect Floor count", idata.floorCount == 2);
		assertTrue("Incorrect MainCorridor count", idata.mainCorridorCount == 1);
		assertTrue("Incorrect SubCorridor count", idata.subCorridorCount == 2);
	}

	public void testParseSensorInput() {
		//SensorData sdata = Parser.getSensorInput();
		SensorData sdata = new SensorData(1, 2, "ON");
		assertTrue("Incorrect Floor number", sdata.floorNumber == 1);
		assertTrue("Incorrect SubCorridor number", sdata.subCorridorNumber == 2);
		assertTrue("Incorrect State", sdata.state.equals("ON"));
	}

}
