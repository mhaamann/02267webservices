/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookingService.Rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.core.MultivaluedMap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author DFS
 */
public class BookingServiceRESTtest {
    
    String city1 = "Copenhagen";
    String city2 = "Berlin";
    String fromDate1 = "2016-01-01";
    String toDate1 = "2016-01-30";
    String toDateFlight1 = "2016-01-10";
    String itinerary1 = "";
    String bookingNumber1 = "1";
    String bookingNumber2 = "2";
    String flightBooking1 = "B12341";
    String hotelBookingNo1 = "7";
    String hotelBookingNo2 = "12";
    
    Client client = Client.create();
        WebResource res = client.resource("http://localhost:8080/BookingRestService/webresources");
    
    
    
    public BookingServiceRESTtest() {
    }

    @Test
    public void testP1() {
        WebResource itineraryResource = client.resource("http://localhost:8080/BookingRestService/webresources/itinerary");
        itinerary1 = res.post(String.class);
        int itineraryId = Integer.parseInt(itinerary1);
        assertTrue(itineraryId > 0);
        
        WebResource flightResource = client.resource("http://localhost:8080/BookingRestService/webresources/flights");
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("origin", city1);
        params.add("destination", city2);
        params.add("departureDate", fromDate1);
        
        String url = res.queryParams(params).get(String.class);
        
        System.err.println(url);
    }
    
    @Test
    public void testP2() {
        String result = res.get(String.class);
        assertEquals(result, "DTU");
    }
    
    @Test
    public void testB() {
        String result = res.get(String.class);
        assertEquals(result, "DTU");
    }
    
    @Test
    public void testC1() {
        String result = res.get(String.class);
        assertEquals(result, "DTU");
    }
    
    @Test
    public void testC2() {
        String result = res.get(String.class);
        assertEquals(result, "DTU");
    }
}
