package Bill;

import reservation.Reservation;

public interface BillingStrategy {
    Bill generateBill(Reservation reservation);
}
