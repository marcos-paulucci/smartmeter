package uk.co.scottishpower.smartmeter;

import uk.co.scottishpower.smartmeter.smartreads.SmartreadDataRestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SmartmeterRestfulserviceApplication.java - Smartmeter Restful webservice Application
 * @author  Marcos Paulucci
 */

@SpringBootApplication
public class SmartmeterRestfulserviceApplication {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/** Smart reads repository */
	@Autowired
	SmartreadDataRestRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SmartmeterRestfulserviceApplication.class, args);
	}
}
