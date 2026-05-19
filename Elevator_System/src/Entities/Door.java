package Entities;

import enums.DoorState;

public class Door {
    private DoorState doorState;
    Door(){
        doorState = DoorState.DOOR_CLOSE;
    }

    public void openDoor(int id){
        doorState = DoorState.DOOR_OPEN;
        System.out.println("Opening the Elevator door of elevator: "+ id);
    }

    public void closeDoor(int id){
        doorState = DoorState.DOOR_CLOSE;
        System.out.println("Closing the Elevator door of elevator: " + id);
    }
}
