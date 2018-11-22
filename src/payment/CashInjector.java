package payment;

public class CashInjector {
	public PaymentApplication getPayment() {
        PaymentFactory paymentFactory = new PaymentFactory();

        Payment payment = paymentFactory.getPayment("CASH");

        return new PaymentApplication(payment);
     }
}
