package eu.greyson.JavaTestRefactor;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Payment {

	private String currency;

	private BigDecimal amount;

	public static Payment parsePayment(String payment) {
		if (Validate.isValidInputFormat(payment)) {
			String[] paymentSplit = payment.split(Constants.SPLIT_SEPARATOR);
			return new Payment(paymentSplit[0].toUpperCase(), new BigDecimal(paymentSplit[1]));
		}
		return null;
	}

	@Override
	public String toString() {	
		return String.join(Constants.PAYMENT_SEPARATOR, Constants.PREFIX, currency, formatToCurrency(amount), System.lineSeparator());
	}
	
	private String formatToCurrency(BigDecimal amount) {
        DecimalFormatSymbols decimalFormatSymbols  = DecimalFormatSymbols.getInstance(new Locale("us", "US"));
		return new DecimalFormat("#,##0.00", decimalFormatSymbols).format(amount).toString(); 
	}
	
	public void updateAmount(BigDecimal amount) {
		this.amount = this.amount.add(amount);
	}
	
	
}
