package com.hotel.power.management;

public class HotelAutomation {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		InputData idata = Parser.getUserInput();
		
		HotelBuilder hotelBuilder = new HotelBuilder();
		Hotel hotel = hotelBuilder.addFloors(idata.floorCount)
				.addMainCorridors(idata.mainCorridorCount)
				.addSubCorridors(idata.subCorridorCount)
				.build();
		
		System.out.println(hotel);
		
		PowerManager audit = new PowerManager(hotel);				// Observer		
		MotionDetector activity = new MotionDetector(audit);		// Observable

		while(true)
		{
			SensorData sdata = Parser.getSensorInput();
			
			if(sdata !=null){
			activity.eventOccurred(sdata);
			System.out.println(hotel);
			}
			
			Thread.sleep(3000);
		}
	}

}
