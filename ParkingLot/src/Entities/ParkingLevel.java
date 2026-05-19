//package Entities;
//
//import enums.VehicleType;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class ParkingLevel {
//
//    private final int levelId;
//
//    private final Map<VehicleType, List<ParkingSpot>> spots;
//
//    public ParkingLevel(int levelId){
//        this.levelId = levelId;
//        this.spots = new HashMap<>();
//        this.spots.put(VehicleType.TWO_WHEELER, new ArrayList<>());
//        this.spots.put(VehicleType.FOUR_WHEELER, new ArrayList<>());
//    }
//
//    public void addSpot(VehicleType type, ParkingSpot spot){
//        spots.get(type).add(spot);
//    }
//
//    public int getLevelId(){
//        return levelId;
//    }
//
//}
