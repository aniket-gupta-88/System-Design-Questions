package pricing;

import Entities.Ticket;

public interface PricingStrategy {

    double calculate(Ticket ticket);
}
