package parkingLot;

import Entities.Ticket;
import payment.Payment;
import pricing.CostComputation;

public class ExitGate {

    private final CostComputation costComputation;

    public ExitGate(CostComputation costComputation){
        this.costComputation = costComputation;
    }

    public void completeExit(ParkingBuilding building, Ticket ticket, Payment payment){
        double amount = calculatePrice(ticket);

        boolean success = payment.pay(amount);
        if(!success){
            throw new RuntimeException("Payment failed.");
        }
        building.release(ticket);
        System.out.println("Exit successful. Gate opened.");
    }

    private double calculatePrice(Ticket ticket){
        return costComputation.compute(ticket);
    }

}
