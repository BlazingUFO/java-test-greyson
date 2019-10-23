package eu.greyson.JavaTestRefactor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Payments {
	
	private Map<String, Payment> paymentMap;
	
	public Payments() {
		paymentMap = new HashMap<>();
	}
	
	public void processPayment(Payment payment) {
		String curenncy = payment.getCurrency();
		BigDecimal amount = payment.getAmount();
		
		if (paymentMap.containsKey(curenncy)) {
			paymentMap.get(curenncy).updateAmount(amount);
			System.out.print(Constants.UPDATED);
		} else {
			paymentMap.put(curenncy, payment);
			System.out.print(Constants.CURRENCY_ADDED + curenncy);
		}
	}

	public String getNetCurrencies() {
		StringBuilder builder = new StringBuilder();
		builder.append(System.lineSeparator());
		paymentMap.forEach((currency, payment) -> {
			if (payment.getAmount() != BigDecimal.ZERO) {
				builder.append(payment);
			}
		});
		return builder.toString();
	}

}
