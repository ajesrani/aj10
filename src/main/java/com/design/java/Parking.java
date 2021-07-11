package com.design.java;

import java.util.*;

public class Parking {
	
	public static void main(String[] args) throws InterruptedException {
		String nameOfParkingLot ="Pintosss Parking Lot";
		
		Map<ParkingSlotType, Map<String,ParkingSlot>>  allSlots = new HashMap<>();
		Map<String,ParkingSlot> largeSlot = new HashMap<>();
		largeSlot.put("C1",new ParkingSlot("C1",ParkingSlotType.Large));
		allSlots.put(ParkingSlotType.Large,largeSlot);
		
		ParkingFloor parkingFloor = new ParkingFloor("1",allSlots);
		List<ParkingFloor> parkingFloors = new ArrayList<>();
        parkingFloors.add(parkingFloor);
        ParkingLot parkingLot = ParkingLot.getInstance(nameOfParkingLot, parkingFloors);
        
        Vehicle vehicle = new Vehicle("KA-01-MA-9999", VehicleCategory.Hatchback);
        
        Ticket ticket = parkingLot.assignTicket(vehicle);
        System.out.println("Ticket number >> "+ ticket.ticketNumber);
        
        Thread.sleep(10000);
		
        double price = parkingLot.scanAndPay(ticket);
        System.out.println("price is >>" + price);
	}	
}

class ParkingLot {
	private String nameofParkingLot;
	private List<ParkingFloor> parkingFloors;
	private static ParkingLot parkingLot = null;

	private ParkingLot(String nameofParkingLot, List<ParkingFloor> parkingFloors) {
		this.nameofParkingLot = nameofParkingLot;
		this.parkingFloors = parkingFloors;
	}
		
	public static ParkingLot getInstance(String nameofParkingLot, List<ParkingFloor> parkingFloors) {
		if(parkingLot == null) {
			parkingLot = new ParkingLot(nameofParkingLot, parkingFloors);
		}
		return parkingLot;
	}
	
	public Ticket assignTicket(Vehicle vehicle) {
		ParkingSlot parkingSlot = getParkingSlotForVehicleAndPark(vehicle);
		if(parkingSlot == null) return null;
		Ticket parkingTicket = createTicketForSlot(parkingSlot,vehicle);
		return parkingTicket;
	}
	
	public double scanAndPay(Ticket ticket) {
		long endTime = System.currentTimeMillis();
		
		ticket.parkingSlot.removeVehicle(ticket.vehicle);
		int duration = (int) (endTime - ticket.startTime)/1000;
		double price = ticket.parkingSlot.parkingSlotType.getPriceForParking(duration);
		return price;
	}
	
	private Ticket createTicketForSlot(ParkingSlot parkingSlot, Vehicle vehicle) {
		return Ticket.createTicket(vehicle,parkingSlot);
	}
	
	private ParkingSlot getParkingSlotForVehicleAndPark(Vehicle vehicle) {
		ParkingSlot parkingSlot=null;
		for(ParkingFloor floor : parkingFloors) {
			parkingSlot = floor.getRelevantSlotForVehicleAndPark(vehicle);
			if(parkingSlot!= null) break;
		}
		return parkingSlot;
	}

	@Override
	public String toString() {
		return "ParkingLot [nameofParkingLot=" + nameofParkingLot + ", parkingFloors=" + parkingFloors + "]";
	}
}

class ParkingFloor {
	String name;
	Map<ParkingSlotType,Map<String,ParkingSlot>> parkingSlots;
	
	public ParkingFloor(String name , Map<ParkingSlotType,Map<String,ParkingSlot>> parkingSlots) {
        this.name=name;
        this.parkingSlots = parkingSlots;
    }
	
	public ParkingSlot getRelevantSlotForVehicleAndPark(Vehicle vehicle) {
		ParkingSlot slot =null;
		ParkingSlotType parkingSlotType = pickCorrectSlot(vehicle.vehicleCategory);
		Map<String,ParkingSlot> relevantSlot = parkingSlots.get(parkingSlotType);
		for(Map.Entry<String,ParkingSlot> m : relevantSlot.entrySet()) {
			if(m.getValue().isAvailable) {
				slot = m.getValue();
                slot.addVehicle(vehicle);
				break;
			}
		}
		
		return slot;
	}
	
	private ParkingSlotType pickCorrectSlot(VehicleCategory vehicleCategory) {
		ParkingSlotType slotType = null;
		if(vehicleCategory.equals(VehicleCategory.TwoWheeler)) slotType = ParkingSlotType.TwoWheeler;
		else slotType = ParkingSlotType.Large;
		
		return slotType;
	}

	@Override
	public String toString() {
		return "ParkingFloor [name=" + name + ", parkingSlots=" + parkingSlots + "]";
	}	
}

class ParkingSlot {
	String name;
    ParkingSlotType parkingSlotType;

    boolean isAvailable = true;
    Vehicle vehicle;
    
    public ParkingSlot(String name, ParkingSlotType parkingSlotType) {
        this.name = name;
        this.parkingSlotType = parkingSlotType;
    }
    
    protected void addVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
        this.isAvailable=false;
    }

    protected void removeVehicle(Vehicle vehicle){
        this.vehicle=null;
        this.isAvailable=true;
    }

	@Override
	public String toString() {
		return "ParkingSlot [name=" + name + ", parkingSlotType=" + parkingSlotType + ", isAvailable=" + isAvailable
				+ ", vehicle=" + vehicle + "]";
	}
}

enum ParkingSlotType {
	
	TwoWheeler{
        public double getPriceForParking(long duration){
            return duration*0.05;
        }
    },
	Large{
        public double getPriceForParking(long duration){
            return duration*0.10;
        }
    };
	
	public abstract double getPriceForParking(long duration);
}


class Vehicle {
    String vehicleNumber;
    VehicleCategory vehicleCategory;
    
    public Vehicle(String vehicleNumber, VehicleCategory vehicleCategory) {
		this.vehicleNumber = vehicleNumber;
		this.vehicleCategory = vehicleCategory;
	}
}

enum VehicleCategory {
    TwoWheeler, Hatchback, Sedan, SUV, Bus
}

class Ticket {
	String ticketNumber;
    long startTime;
    long endTime;
    Vehicle vehicle;
    ParkingSlot parkingSlot;

	public Ticket(Vehicle vehicle, ParkingSlot parkingSlot) {
		this.vehicle = vehicle;
		this.parkingSlot = parkingSlot;
		this.ticketNumber = vehicle.vehicleNumber+System.currentTimeMillis();
		this.startTime = System.currentTimeMillis();
	}

	public static Ticket createTicket(Vehicle vehicle, ParkingSlot parkingSlot) {
		return new Ticket(vehicle, parkingSlot);
	}
}
