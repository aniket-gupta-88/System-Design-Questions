package payment;

import Bill.Bill;

public interface PaymentStrategy {
    Payment processPayment(Bill bill, double paymentAmount);
}
