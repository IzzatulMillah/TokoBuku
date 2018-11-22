package payment;

public class CreditInjector {
	public PaymentApplication getPayment() {
		PaymentFactory paymentFactory = new PaymentFactory();

		Payment payment = paymentFactory.getPayment("CREDIT");

		return new PaymentApplication(payment);
	}
}
