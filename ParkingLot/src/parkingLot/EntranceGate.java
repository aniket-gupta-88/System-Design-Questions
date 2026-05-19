package parkingLot;

import Entities.Ticket;
import Entities.Vehicle;

public class EntranceGate {

    public Ticket enter(ParkingBuilding building, Vehicle vehicle){
        return building.allocate(vehicle);
    }
}
