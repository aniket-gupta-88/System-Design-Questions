package Entities;

import enums.VehicleType;

public class Vehicle {
    String vechicleNumber;
    VehicleType vehicleType;

     public Vehicle(String vechicleNumber, VehicleType vehicleType){
         this.vechicleNumber = vechicleNumber;
         this.vehicleType = vehicleType;
     }

     public String getVechicleNumber(){
         return vechicleNumber;
     }

     public VehicleType getVehicleType(){
         return vehicleType;
     }
}
