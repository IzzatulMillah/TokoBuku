package payment;

public class CashPaymentImpl implements Payment{

	@Override
	public void pay(String msg, String rec) {
		//logic to pay cash
        System.out.println("Cash payment "+rec+ " with Message="+msg);
	}
}
