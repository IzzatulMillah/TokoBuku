package payment;

public class CreditPaymentImpl implements Payment{

	@Override
	public void pay(String msg, double rec) {
		//logic to pay with credit card
        System.out.println("Credit payment "+rec+ " with Message="+msg);
	}
}
