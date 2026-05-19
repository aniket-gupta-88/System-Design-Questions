package pricing;

import Entities.Ticket;

public class FixedPrincingStrategy implements PricingStrategy {

    @Override
    public double calculate(Ticket ticket){
        return 100;
    }
}
