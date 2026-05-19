import Entities.Building;
import Entities.ElevatorCar;
import Entities.InternalButton;
import controllers.ElevatorController;
import controllers.ElevatorScheduler;
import controllers.ExternalDispatcher;
import controllers.NearestElevatorStrategy;

import java.util.Arrays;

public class ElevatorClient {

    public static void main(String[] args) {
        try{
            ElevatorCar car1 = new ElevatorCar(1);
            ElevatorController controller1 = new ElevatorController(car1);

            ElevatorCar car2 = new ElevatorCar(2);
            ElevatorController controller2 = new ElevatorController(car2);

            InternalButton internalButton_for_elevator1 = new InternalButton(controller1);
            InternalButton internalButton_for_elevator2 = new InternalButton(controller2);

            ElevatorScheduler elevatorScheduler = new ElevatorScheduler(
                    Arrays.asList(controller1, controller2),
                    new NearestElevatorStrategy()
            );

            ExternalDispatcher externalDispatcher = new ExternalDispatcher(elevatorScheduler);

            Building building = new Building(5, externalDispatcher);

            new Thread(controller1, "Elevator-1").start();
            new Thread(controller2, "Elevator-2").start();

            building.getFloor(3).pressUpButton();
            Thread.sleep(5);

            building.getFloor(5).pressDownButton();
            Thread.sleep(5);

            internalButton_for_elevator1.pressButton(4); // user inside elevator 1 presses floor 4
            Thread.sleep(5);

            internalButton_for_elevator1.pressButton(5); // user inside elevator 1 presses floor 5
            Thread.sleep(5);

            building.getFloor(1).pressDownButton();
            Thread.sleep(5);

            building.getFloor(2).pressUpButton();
            Thread.sleep(5);

            internalButton_for_elevator1.pressButton(2);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
