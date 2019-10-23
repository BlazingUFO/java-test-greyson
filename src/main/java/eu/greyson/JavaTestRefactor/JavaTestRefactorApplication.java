package eu.greyson.JavaTestRefactor;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@SpringBootApplication
public class JavaTestRefactorApplication implements CommandLineRunner {

	Boolean isReading;
	Payments payments;


	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(JavaTestRefactorApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.setLogStartupInfo(false);
		app.run(args);
	}

	@Override
	public void run(String... args) {
		isReading = true;
		payments = new Payments();
		trackerService();
	}

	private void trackerService() {
		System.out.println(Constants.SEPARATOR + System.lineSeparator() + Constants.HEADER + System.lineSeparator() + Constants.SEPARATOR);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		while (isReading) {
			
			readInput(bufferedReader);
					
		}

		System.out.println(Constants.SEPARATOR + System.lineSeparator() + Constants.VALIDATION_EXIT);
	}

	public void readInput(BufferedReader bufferedReader) {
		System.out.print(Constants.INPUT);
		String inputLine = null;

		try {
			inputLine = bufferedReader.readLine();

			switch (inputLine.toLowerCase()) {
			case Constants.QUIT:
				isReading = false;
				return;
			case Constants.SHOW_HELP:
				System.out.println(Constants.HELP);
				return;
			default:
				Payment payment = Payment.parsePayment(inputLine);
				if (payment != null) {
					payments.processPayment(payment);
				}
				System.out.println(Constants.ACTUAL_BALANCE + payments.getNetCurrencies());
			}
		
		} catch (Exception e) {
			System.out.println(Constants.VALIDATION_INVALID_INPUT + inputLine);
		}
	}
	
}