package payment;

public class CashPayment implements Payment{
    @Override
    public boolean pay(double amount) {
        System.out.println("Cash Paid " + amount);
        return true;
    }
}
