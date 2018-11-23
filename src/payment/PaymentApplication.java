package payment;

public class PaymentApplication {
	private Payment payment;

    public PaymentApplication(Payment service){
       this.payment = service;
    }

    public void paymentProcess(String msg, double rec){
       //do some msg validation, manipulation logic etc
       this.payment.pay(msg, rec);
    }
}
