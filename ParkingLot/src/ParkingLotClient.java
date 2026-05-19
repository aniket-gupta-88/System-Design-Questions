import Entities.ParkingSpot;
import Entities.Ticket;
import Entities.Vehicle;
import LookupStrategy.ParkingSpotLookupStrategy;
import LookupStrategy.RandomLookupStrategy;
import enums.VehicleType;
import parkingLot.*;
import payment.CashPayment;
import payment.UPIPayment;
import pricing.CostComputation;
import pricing.FixedPrincingStrategy;
import spotManagers.FourWheelerSpotManager;
import spotManagers.ParkingSpotManager;
import spotManagers.TwoWheelerSpotManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotClient {
    public static void main(String[] args) {

        ParkingSpotLookupStrategy strategy = new RandomLookupStrategy();

        Map<VehicleType, ParkingSpotManager> level0Manager = new HashMap<>();
        level0Manager.put(VehicleType.TWO_WHEELER,
                new TwoWheelerSpotManager(List.of(new ParkingSpot("L1-s1"), new ParkingSpot("L1-S2")), strategy));

        ParkingLevel level1 = new ParkingLevel(1,level0Manager);

        Map<VehicleType, ParkingSpotManager> level1Managers = new HashMap<>();
        level1Managers.put(VehicleType.TWO_WHEELER,
                new TwoWheelerSpotManager(List.of(new ParkingSpot("L2-S1")), strategy));

        level1Managers.put(VehicleType.FOUR_WHEELER,
                new FourWheelerSpotManager(List.of(new ParkingSpot("L2-S2"),
                        new ParkingSpot("L2-S3")), strategy));

        ParkingLevel level2 = new ParkingLevel(2,level1Managers);

        ParkingBuilding parkingBuilding = new ParkingBuilding(List.of(level1,level2), new CostComputation(new FixedPrincingStrategy()));
        ParkingLot parkingLot = new ParkingLot(parkingBuilding,new EntranceGate(), new ExitGate(new CostComputation(new FixedPrincingStrategy())));

        Vehicle bike = new Vehicle("Bike-101", VehicleType.TWO_WHEELER);
        Vehicle car = new Vehicle("Car-201", VehicleType.FOUR_WHEELER);

        Ticket t1 = parkingLot.vehicleArrives(bike);
        Ticket t2 = parkingLot.vehicleArrives(car);

        parkingLot.vehicleExits(t1, new CashPayment());
        parkingLot.vehicleExits(t2, new UPIPayment());
    }
}