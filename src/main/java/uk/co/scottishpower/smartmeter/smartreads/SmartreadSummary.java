package uk.co.scottishpower.smartmeter.smartreads;


import org.springframework.data.rest.core.config.Projection;

/**
 * SmartreadSummary.java - A projection of the real entity to return only the required fields from the endpoint.
 * @author  Marcos Paulucci
 * see @Smartread.java
 */


@Projection(name = "smartreadSummary", types = { Smartread.class })
public interface SmartreadSummary {

    double getELEC_SMART_READ();
    double getGAS_SMART_READ();

}