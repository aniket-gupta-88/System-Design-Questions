package controllers;

import enums.ElevatorDirection;

import java.util.List;

public class ElevatorScheduler {

    private final List<ElevatorController> controllers;
    private ElevatorSelectionStrategy strategy;

    public ElevatorScheduler(List<ElevatorController> controllers, ElevatorSelectionStrategy strategy){
        this.controllers = controllers;
        this.strategy = strategy;
    }

    public void setStrategy(ElevatorSelectionStrategy strategy){
        this.strategy = strategy;
    }

    public ElevatorController assignElevator(int floor, ElevatorDirection direction){
        return strategy.selectElevator(controllers, floor, direction);
    }

    public static class ExternalDispatcher {
        ElevatorScheduler scheduler;

        public ExternalDispatcher(ElevatorScheduler scheduler){
            this.scheduler = scheduler;
        }

        public void submitExternalRequest(int floor, ElevatorDirection direction){
            ElevatorController controller = scheduler.assignElevator(floor, direction);
            controller.submitRequest(floor);
        }

    }
}
