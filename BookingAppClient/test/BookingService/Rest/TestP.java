/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookingService.Rest;

import ExternalBookingService.Booking;
import ExternalBookingService.FlightInfo;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import dk.dtu.xml.BookingContainer;
import dk.dtu.xml.Flight;
import dk.dtu.xml.FlightContainer;
import dk.dtu.xml.Hotel;
import dk.dtu.xml.ItineraryContainer;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matthias Haamann.
 */
public class TestP {

    Client client = Client.create();
    WebResource itineraryResource = client.resource("http://localhost:8080/BookingRestService/webresources/itinerary");
    WebResource flightsResource = client.resource("http://localhost:8080/BookingRestService/webresources/flights");
    WebResource hotelsResource = client.resource("http://localhost:8080/BookingRestService/webresources/hotels");
 
    
    public TestP() {
    }

    @Test
    public void testP1() {
        // List that holds the hotel and flight selection.
        LinkedList<String> hotelBookingList = new LinkedList<>();
        LinkedList<String> flightBookingList = new LinkedList<>();
        
        // Create itinerary Id.
        String itineraryId = itineraryResource.post(String.class);
        System.out.print(itineraryId);

        // Prepare Get hotels query params.
        MultivaluedMap<String, String> hotelQueryParams = new MultivaluedMapImpl();
        hotelQueryParams.add("city", "Copenhagen");
        hotelQueryParams.add("arrivalDate", "2016-02-01");
        hotelQueryParams.add("departureDate", "2016-02-5");
        BookingContainer hotels = hotelsResource.queryParams(hotelQueryParams).get(new GenericType<BookingContainer>() {
        });

        for (Booking booking : hotels.hotel) {
            hotelBookingList.add(booking.getBookingNumber());
        }

        // Prepare Get flights query params - For FLIGHT 1.
        MultivaluedMap<String, String> queryParamsFlights = new MultivaluedMapImpl();
        queryParamsFlights.add("origin", "Copenhagen");
        queryParamsFlights.add("destination", "Berlin");
        queryParamsFlights.add("departureDate", "2016-01-01");
        FlightContainer flights = flightsResource.queryParams(queryParamsFlights).get(new GenericType<FlightContainer>() {
        });

        for (FlightInfo flight : flights.flight) {
           flightBookingList.add(flight.getBookingNumber());
        }
        
        // Prepare Get flights query params - For FLIGHT 2.
        queryParamsFlights = new MultivaluedMapImpl();
        queryParamsFlights.add("origin", "Berlin");
        queryParamsFlights.add("destination", "Amsterdam");
        queryParamsFlights.add("departureDate", "2016-01-01");
        flights = flightsResource.queryParams(queryParamsFlights).get(new GenericType<FlightContainer>() {
        });

        for (FlightInfo flight : flights.flight) {
           flightBookingList.add(flight.getBookingNumber());
        }
        
        // Prepare Get flights query params - For FLIGHT 3.
        queryParamsFlights = new MultivaluedMapImpl();
        queryParamsFlights.add("origin", "Amsterdam");
        queryParamsFlights.add("destination", "Paris");
        queryParamsFlights.add("departureDate", "2016-01-01");
        flights = flightsResource.queryParams(queryParamsFlights).get(new GenericType<FlightContainer>() {
        });

        for (FlightInfo flight : flights.flight) {
           flightBookingList.add(flight.getBookingNumber());
        }
        
        // first planning a flight (i.e. getting a list of flights and then
        // adding a flight to the itinerary), then by planning a hotel, another
        // flight, a third flight, and finally a hotel.
        addFlight(itineraryId, flightBookingList.pop());
        addHotel(itineraryId, hotelBookingList.pop());
        addFlight(itineraryId, flightBookingList.pop());
        addHotel(itineraryId, hotelBookingList.pop());
        addFlight(itineraryId, flightBookingList.pop());
        addHotel(itineraryId, hotelBookingList.pop());
        
        ItineraryContainer itinerary = 
                itineraryResource.path(itineraryId).get(new GenericType<ItineraryContainer>() {});
        
        for (Flight flight : itinerary.itinerary.flights) {
            assertEquals("unconfirmed", flight.status);
        }
        
        
        for (Hotel hotel : itinerary.itinerary.hotels) {
            assertEquals("unconfirmed", hotel.status);
        }
        // Ensure that we have 3 hotels and 3 flights on our itinerary.
        assertEquals(itinerary.itinerary.flights.size(), 3);
        assertEquals(itinerary.itinerary.hotels.size(), 3);
        
        // Book the itinerary.
        
        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add("name", "Anne Strandberg"); // Person with unlimited funds
        formData.add("number", "50408816");
        formData.add("year", "9");
        formData.add("month", "5");
        String s_response = itineraryResource.path(itineraryId).type(MediaType.APPLICATION_FORM_URLENCODED).put(String.class, formData);
        
        itinerary = itineraryResource.path(itineraryId).get(new GenericType<ItineraryContainer>() {});
        
        for (Flight flight : itinerary.itinerary.flights) {
            assertEquals("confirmed", flight.status);
        }
        
        for (Hotel hotel : itinerary.itinerary.hotels) {
            assertEquals("confirmed", hotel.status);
        }
        // Ensure that we have 3 hotels and 3 flights on our itinerary.
        assertEquals(itinerary.itinerary.flights.size(), 3);
        assertEquals(itinerary.itinerary.hotels.size(), 3);
        
    }
    
    @Test
    public void testP2() {
        // List that holds the hotel and flight selection.
        LinkedList<String> hotelBookingList = new LinkedList<>();
        LinkedList<String> flightBookingList = new LinkedList<>();
        
        // Create itinerary Id.
        String itineraryId = itineraryResource.post(String.class);
        System.out.print(itineraryId);

        // Prepare Get flights query params - For FLIGHT 1.
        MultivaluedMap<String, String> queryParamsFlights = new MultivaluedMapImpl();
        queryParamsFlights.add("origin", "Copenhagen");
        queryParamsFlights.add("destination", "Berlin");
        queryParamsFlights.add("departureDate", "2016-01-01");
        FlightContainer flights = flightsResource.queryParams(queryParamsFlights).get(new GenericType<FlightContainer>() {
        });

        for (FlightInfo flight : flights.flight) {
           flightBookingList.add(flight.getBookingNumber());
        }
        
        // Add flight to itinerary.
        addFlight(itineraryId, flightBookingList.pop());
        
        ItineraryContainer itinerary = 
                itineraryResource.path(itineraryId).get(new GenericType<ItineraryContainer>() {});
        
        for (Flight flight : itinerary.itinerary.flights) {
            assertEquals("unconfirmed", flight.status);
        }
        
        // Ensure that we have 0 hotels and 1 flights on our itinerary.
        assertEquals(itinerary.itinerary.flights.size(), 1);
        assertEquals(itinerary.itinerary.hotels.size(), 0);
        
        // Book the itinerary.
        
        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add("name", "Anne Strandberg"); // Person with unlimited funds
        formData.add("number", "50408816");
        formData.add("year", "9");
        formData.add("month", "5");
        String s_response = itineraryResource.path(itineraryId).type(MediaType.APPLICATION_FORM_URLENCODED).put(String.class, formData);
        
        itinerary = itineraryResource.path(itineraryId).get(new GenericType<ItineraryContainer>() {});
        
        for (Flight flight : itinerary.itinerary.flights) {
            assertEquals("confirmed", flight.status);
        }
        
        // Ensure that we have 0 hotels and 1 flights on our itinerary.
        assertEquals(itinerary.itinerary.flights.size(), 1);
        assertEquals(itinerary.itinerary.hotels.size(), 0);
        
        // Now cancel the itinerary.
        itineraryResource.path(itineraryId).delete(String.class);
        
        itinerary = itineraryResource.path(itineraryId).get(new GenericType<ItineraryContainer>() {});
        
        for (Flight flight : itinerary.itinerary.flights) {
            assertEquals("cancelled", flight.status);
        }
        
        // Ensure that we have 0 hotels and 1 flights on our itinerary.
        assertEquals(itinerary.itinerary.flights.size(), 1);
        assertEquals(itinerary.itinerary.hotels.size(), 0);
    }
    
    public ClientResponse addFlight(String itineraryId, String bookingNumber) {
        // Prepare Add flight query params.
        MultivaluedMap queryParamsAddFlight = new MultivaluedMapImpl();
        queryParamsAddFlight.add("itineraryId", itineraryId);
        queryParamsAddFlight.add("bookingNumber", bookingNumber);
        // Add flight.
        return flightsResource.queryParams(queryParamsAddFlight).post(ClientResponse.class);
    }
    
    public ClientResponse addHotel(String itineraryId, String bookingNumber) {
        // Prepare Add hotel query params.
        MultivaluedMap queryParamsAddHotel = new MultivaluedMapImpl();
        queryParamsAddHotel.add("itineraryId", itineraryId);
        queryParamsAddHotel.add("bookingNumber", bookingNumber);
        // Add hotel.
        return hotelsResource.queryParams(queryParamsAddHotel).post(ClientResponse.class);
    }
    
}
