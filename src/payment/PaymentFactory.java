package payment;

public class PaymentFactory {
	public Payment getPayment(String paymentType){
		if(paymentType == null){
			return null;
		}
		
		if(paymentType.equalsIgnoreCase("CASH")){
			return new CashPaymentImpl();
		} else if(paymentType.equalsIgnoreCase("CREDIT")){
			return new CreditPaymentImpl();
		}

		return null;
	}
}
