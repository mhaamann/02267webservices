/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookingService.Rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import dk.dtu.xml.BookingContainer;
import dk.dtu.xml.FlightContainer;
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
        WebResource itineraryResource = client.resource("http://localhost:8080/BookingRestService/webresources/itinerary");
        WebResource flightsResource = client.resource("http://localhost:8080/BookingRestService/webresources/flights");
        WebResource hotelsResource = client.resource("http://localhost:8080/BookingRestService/webresources/hotels");
        
    
    
    public BookingServiceRESTtest() {
    }

    @Test
    public void testP1() {
        itinerary1 = itineraryResource.post(String.class);
        int itineraryId = Integer.parseInt(itinerary1);
        assertTrue(itineraryId > 0);
        System.err.println(itinerary1);
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("origin", city1);
        params.add("destination", city2);
        params.add("departureDate", fromDate1);
        FlightContainer flightContainer = flightsResource.queryParams(params).get(new GenericType<FlightContainer>() {});
        
        MultivaluedMap<String,String> qParams = new MultivaluedMapImpl();
        qParams.add("bookintNumber", flightContainer.flight.get(0).getBookingNumber());
        qParams.add("itineraryId", itinerary1);
        flightsResource.queryParams(qParams).post();
        
        MultivaluedMap<String, String> params5 = new MultivaluedMapImpl();
        params5.add("city", city1);
        params5.add("arrivalDate", fromDate1);
        params5.add("departureDate", toDate1);
        BookingContainer bookingContainer = hotelsResource.queryParams(params5).get(new GenericType<BookingContainer>() {});
        
        MultivaluedMap<String,String> qParams4 = new MultivaluedMapImpl();
        qParams4.add("bookintNumber", bookingContainer.hotel.get(0).getBookingNumber());
        qParams4.add("itineraryId", itinerary1);
        hotelsResource.queryParams(qParams4).post();
        
        MultivaluedMap<String, String> params4 = new MultivaluedMapImpl();
        params4.add("origin", city1);
        params4.add("destination", city2);
        params4.add("departureDate", fromDate1);
        FlightContainer flightContainer2 = flightsResource.queryParams(params4).get(new GenericType<FlightContainer>() {});
        
        MultivaluedMap<String,String> qParams2 = new MultivaluedMapImpl();
        qParams2.add("bookintNumber", flightContainer2.flight.get(0).getBookingNumber());
        qParams2.add("itineraryId", itinerary1);
        flightsResource.queryParams(qParams2).post();
       
        MultivaluedMap<String, String> params3 = new MultivaluedMapImpl();
        params3.add("origin", city1);
        params3.add("destination", city2);
        params3.add("departureDate", fromDate1);
        FlightContainer flightContainer3 = flightsResource.queryParams(params3).get(new GenericType<FlightContainer>() {});
        
        MultivaluedMap<String,String> qParams3 = new MultivaluedMapImpl();
        qParams3.add("bookintNumber", flightContainer3.flight.get(0).getBookingNumber());
        qParams3.add("itineraryId", itinerary1);
        flightsResource.queryParams(qParams3).post();
        
        MultivaluedMap<String, String> params6 = new MultivaluedMapImpl();
        params6.add("city", city1);
        params6.add("arrivalDate", fromDate1);
        params6.add("departureDate", toDate1);
        BookingContainer bookingContainer2 = hotelsResource.queryParams(params6).get(new GenericType<BookingContainer>() {});
        
        MultivaluedMap<String,String> qParams7 = new MultivaluedMapImpl();
        qParams7.add("bookintNumber", bookingContainer2.hotel.get(0).getBookingNumber());
        qParams7.add("itineraryId", itinerary1);
        hotelsResource.queryParams(qParams7).post();
    }
    /*
    @Test
    public void testP2() {
        String result = res.get(String.class);
        assertEquals(result, "DTU");
    }
    
    @Test
    public void testB() {
        String result = res.get(String.class);
        assertEquals(result, "DTU");
    }*/
    
}
