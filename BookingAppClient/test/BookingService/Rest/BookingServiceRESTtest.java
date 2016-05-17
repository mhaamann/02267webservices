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
import dk.dtu.xml.Flight;
import dk.dtu.xml.FlightContainer;
import dk.dtu.xml.Hotel;
import dk.dtu.xml.ItineraryContainer;
import javax.ws.rs.core.MediaType;
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
        
        MultivaluedMap<String, String> getQueryParametersForFlight_1 = new MultivaluedMapImpl();
        getQueryParametersForFlight_1.add("origin", city1);
        getQueryParametersForFlight_1.add("destination", city2);
        getQueryParametersForFlight_1.add("departureDate", fromDate1);
        FlightContainer flightContainer = flightsResource.queryParams(getQueryParametersForFlight_1).get(new GenericType<FlightContainer>() {});
        
        MultivaluedMap<String,String> addQueryParametersForFlight_1 = new MultivaluedMapImpl();
        addQueryParametersForFlight_1.add("bookintNumber", flightContainer.flight.get(0).getBookingNumber());
        addQueryParametersForFlight_1.add("itineraryId", itinerary1);
        flightsResource.queryParams(addQueryParametersForFlight_1).post();
        
        MultivaluedMap<String, String> getQueryParametersForHotel_1 = new MultivaluedMapImpl();
        getQueryParametersForHotel_1.add("city", city1);
        getQueryParametersForHotel_1.add("arrivalDate", fromDate1);
        getQueryParametersForHotel_1.add("departureDate", toDate1);
        BookingContainer bookingContainer = hotelsResource.queryParams(getQueryParametersForHotel_1).get(new GenericType<BookingContainer>() {});
        
        MultivaluedMap<String,String> addQueryParametersForHotel_1 = new MultivaluedMapImpl();
        addQueryParametersForHotel_1.add("bookintNumber", bookingContainer.hotel.get(0).getBookingNumber());
        addQueryParametersForHotel_1.add("itineraryId", itinerary1);
        hotelsResource.queryParams(addQueryParametersForHotel_1).post();
        
        MultivaluedMap<String, String> getQueryParametersForFlight_2 = new MultivaluedMapImpl();
        getQueryParametersForFlight_2.add("origin", city1);
        getQueryParametersForFlight_2.add("destination", city2);
        getQueryParametersForFlight_2.add("departureDate", fromDate1);
        FlightContainer flightContainer2 = flightsResource.queryParams(getQueryParametersForFlight_2).get(new GenericType<FlightContainer>() {});
        
        MultivaluedMap<String,String> addQueryParametersForFlight_2 = new MultivaluedMapImpl();
        addQueryParametersForFlight_2.add("bookintNumber", flightContainer2.flight.get(0).getBookingNumber());
        addQueryParametersForFlight_2.add("itineraryId", itinerary1);
        flightsResource.queryParams(addQueryParametersForFlight_2).post();
       
        MultivaluedMap<String, String> getQueryParametersForFlight_3 = new MultivaluedMapImpl();
        getQueryParametersForFlight_3.add("origin", city1);
        getQueryParametersForFlight_3.add("destination", city2);
        getQueryParametersForFlight_3.add("departureDate", fromDate1);
        FlightContainer flightContainer3 = flightsResource.queryParams(getQueryParametersForFlight_3).get(new GenericType<FlightContainer>() {});
        
        MultivaluedMap<String,String> addQueryParametersForFlight_3 = new MultivaluedMapImpl();
        addQueryParametersForFlight_3.add("bookintNumber", flightContainer3.flight.get(0).getBookingNumber());
        addQueryParametersForFlight_3.add("itineraryId", itinerary1);
        flightsResource.queryParams(addQueryParametersForFlight_3).post();
        
        MultivaluedMap<String, String> getQueryParametersForHotel_2 = new MultivaluedMapImpl();
        getQueryParametersForHotel_2.add("city", city1);
        getQueryParametersForHotel_2.add("arrivalDate", fromDate1);
        getQueryParametersForHotel_2.add("departureDate", toDate1);
        BookingContainer bookingContainer2 = hotelsResource.queryParams(getQueryParametersForHotel_2).get(new GenericType<BookingContainer>() {});
        
        MultivaluedMap<String,String> addQueryParametersForHotel_2 = new MultivaluedMapImpl();
        addQueryParametersForHotel_2.add("bookintNumber", bookingContainer2.hotel.get(0).getBookingNumber());
        addQueryParametersForHotel_2.add("itineraryId", itinerary1);
        hotelsResource.queryParams(addQueryParametersForHotel_2).post();
        
        
        ItineraryContainer itinerary = 
                itineraryResource.path(itinerary1).get(new GenericType<ItineraryContainer>() {});
        
        
        for (Flight flight : itinerary.itinerary.flights) {
            assertEquals("unconfirmed", flight.status);
        }
        
        for (Hotel hotel : itinerary.itinerary.hotels) {
            assertEquals("unconfirmed", hotel.status);
        }
        
        //================ 
        
        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add("year", "9");
        formData.add("month", "6");
        formData.add("number", "50408818");
        formData.add("name", "Donovan Jasper");
        String response = itineraryResource.path(itinerary1).type(MediaType.APPLICATION_FORM_URLENCODED).put(String.class, formData);
        
        itinerary = 
                itineraryResource.path(itinerary1).get(new GenericType<ItineraryContainer>() {});
        
        
        for (Flight flight : itinerary.itinerary.flights) {
            assertEquals("confirmed", flight.status);
        }
        
        for (Hotel hotel : itinerary.itinerary.hotels) {
            assertEquals("confirmed", hotel.status);
        }
        
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
