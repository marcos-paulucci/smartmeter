package uk.co.scottishpower.smartmeter.smartreads;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import uk.co.scottishpower.smartmeter.SmartmeterRestfulserviceApplication;

import java.net.URI;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SmartmeterRestfulserviceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTests {

    @Value("${local.server.port}")
    private int port;

    private String getBaseUri(){
        return "http://localhost:" + port + "/api/smart/reads";
    }


    /**
     * Get request to valid smartread Uri.
     * @result Http result status should be Ok
     */
    @Test
    public void testGetExistingSmartreadShouldRetrieveHTTPOk() {
        ResponseEntity<Resource<Smartread>> responseEntity = new RestTemplate().exchange( getBaseUri() +  "/2", HttpMethod.GET, null, new ParameterizedTypeReference<Resource<Smartread>>() {}, Collections.emptyMap());
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    /**
     * Get request to all existing read resources at /reads.
     * @result Result should contain the two reads created as test data
     */
    @Test
    public void getAllReads(){

        try {
            ParameterizedTypeReference<Resources<Smartread>> typeReference = new ParameterizedTypeReference<Resources<Smartread>>() {
            };
            Traverson traverson = new Traverson(new URI(getBaseUri()), MediaTypes.HAL_JSON);
            Resources<Smartread> t = traverson.follow().toObject(typeReference);

            ArrayList<Smartread> list = new ArrayList<Smartread>();
            list.addAll(t.getContent());
            Assert.assertEquals(list.size(), 2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private Smartread getSingleReadRequest(String accountNumber){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Resource<Smartread>> responseEntity = restTemplate.exchange(getBaseUri() + "/" + accountNumber, HttpMethod.GET, null, new ParameterizedTypeReference<Resource<Smartread>>() {}, Collections.emptyMap());
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            Resource<Smartread> smartreadResource = responseEntity.getBody();
            return smartreadResource.getContent();
        }
        return new Smartread();
    }

    /**
     * Get request to single smartread at /reads/1
     * @result Result should contain the smartread with its proper test data
     */
    @Test
    public void testGetSmartreadShouldRetrieveId1() {
        Smartread smartread= this.getSingleReadRequest("1");
        Assert.assertEquals(smartread.getACCOUNT_NUMBER(), new Long(1));
        Assert.assertEquals(smartread.getELEC_ID(), new Long(1));
        Assert.assertEquals(smartread.getGAS_ID(), new Long(1));
        Assert.assertEquals(smartread.getELEC_SMART_READ(), 230.4, 0.001);
        Assert.assertEquals(smartread.getGAS_SMART_READ(), 1200.6, 0.001);
    }

    /**
     * Get request to single smartread at /reads/w
     * @result Result should contain the smartread with its proper test data
     */
    @Test
    public void testGetSmartreadShouldRetrieveId2() {
        Smartread smartread= this.getSingleReadRequest("2");
        Assert.assertEquals(smartread.getACCOUNT_NUMBER(), new Long(2));
        Assert.assertEquals(smartread.getELEC_ID(), new Long(1));
        Assert.assertEquals(smartread.getGAS_ID(), new Long(1));
        Assert.assertEquals(smartread.getELEC_SMART_READ(), 145.65, 0.001);
        Assert.assertEquals(smartread.getGAS_SMART_READ(), 345.6, 0.001);
    }

    /**
     * Get request to an Non existent smartread at /reads/999
     * @result HTTP Result code should be NOTFOUND/404
     */
    @Test
    public void testNonexistentSmartReadShouldReturn404() {
        try {
            ResponseEntity<Resource<Smartread>> responseEntity = new RestTemplate().exchange( getBaseUri() +  "/999", HttpMethod.GET, null, new ParameterizedTypeReference<Resource<Smartread>>() {}, Collections.emptyMap());
        } catch (Exception e){
            Assert.assertEquals(((HttpClientErrorException)e).getStatusCode(), HttpStatus.NOT_FOUND);
        }


    }
}