package com.hotel.power.management;

import java.util.Scanner;

class InputData
{
	public InputData() {}
	
	public InputData(int floorCount, int mainCorridorCount, int subCorridorCount) {
		this.floorCount = floorCount;
		this.mainCorridorCount = mainCorridorCount;
		this.subCorridorCount = subCorridorCount;
		
	}

	int floorCount;
	int mainCorridorCount;
	int subCorridorCount;
}

class SensorData
{
	public SensorData() {}
	
	public SensorData(int floorNumber, int subCorridorNumber, String state) {
		this.floorNumber = floorNumber;
		this.subCorridorNumber = subCorridorNumber;
		this.state = state;
	}
	
	int floorNumber;
	int subCorridorNumber;
	String state;
}

public class Parser {
	
	private static final String NUMBER_OF_FLOORS = "Number of floors: ";
	private static final String MAIN_CORRIDORS_PER_FLOOR = "Main Corridors per floor: ";
	private static final String SUB_CORRIDORS_PER_FLOOR = "Sub Corridors per floor: ";
	private static final String SENSORS_INPUT = "Sensors input";
	private static final String FLOOR = "FLOOR: ";
	private static final String SUB_CORRIDOR = "Sub corridor: ";
	private static final String State = "State(ON/OFF): ";
	private static final String INVALID_INPUT = "Please enter valid Input";
	private static final Scanner input = new Scanner(System.in);

	public static InputData getUserInput() {
		
		InputData data = new InputData();
		System.out.print(NUMBER_OF_FLOORS);
		parseInput();
		data.floorCount = Integer.parseInt(input.nextLine());
		System.out.print(MAIN_CORRIDORS_PER_FLOOR);
		parseInput();
		data.mainCorridorCount = Integer.parseInt(input.nextLine());
		System.out.print(SUB_CORRIDORS_PER_FLOOR);
		parseInput();
		data.subCorridorCount = Integer.parseInt(input.nextLine());
		System.out.println();
		//input.close();
		
		return data;
	}
	
	public static SensorData getSensorInput() {
		
		SensorData data = new SensorData();
		System.out.println(SENSORS_INPUT);
		System.out.print(FLOOR);
		parseInput();
		data.floorNumber = Integer.parseInt(input.nextLine());
		System.out.print(SUB_CORRIDOR);
		parseInput();
		data.subCorridorNumber = Integer.parseInt(input.nextLine());
		System.out.print(State);
		data.state = input.nextLine();
		System.out.println();
		//input.close();
		
		return data;
	}
	
	private static void parseInput() {

		while(!input.hasNextInt()) {
			System.err.println(INVALID_INPUT);
			input.nextLine();
		}
	}
}

