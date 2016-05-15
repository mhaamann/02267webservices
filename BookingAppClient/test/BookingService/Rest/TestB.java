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
import dk.dtu.xml.ItineraryContainer;
import dk.dtu.xml.Hotel;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matthias Haamann
 */
public class TestB {

    Client client = Client.create();
    WebResource itineraryResource = client.resource("http://localhost:8080/BookingRestService/webresources/itinerary");
    WebResource flightsResource = client.resource("http://localhost:8080/BookingRestService/webresources/flights");
    WebResource hotelsResource = client.resource("http://localhost:8080/BookingRestService/webresources/hotels");

    public TestB() {
    }

    @Test
    public void testB() {

        // Create itinerary Id.
        String itineraryId = itineraryResource.post(String.class);
        System.out.print(itineraryId);

        // Prepare Get hotels query params.
        MultivaluedMap<String, String> hotelQueryParams = new MultivaluedMapImpl();
        hotelQueryParams.add("city", "Amsterdam");
        hotelQueryParams.add("arrivalDate", "2016-01-01");
        hotelQueryParams.add("departureDate", "2016-01-5");
        BookingContainer hotels = hotelsResource.queryParams(hotelQueryParams).get(new GenericType<BookingContainer>() {
        });

        int count = 1;
        for (Booking booking : hotels.hotel) {
            // Prepare Add hotel query params.
            MultivaluedMap queryParamsAddHotel = new MultivaluedMapImpl();
            queryParamsAddHotel.add("itineraryId", itineraryId);
            queryParamsAddHotel.add("bookingNumber", booking.getBookingNumber());
            // Add hotel.
            ClientResponse response = hotelsResource.queryParams(queryParamsAddHotel).post(ClientResponse.class);
            if (count == 2) {
                break;
            }
            count++;

        }

        // Prepare Get flights query params.
        MultivaluedMap<String, String> queryParamsFlights = new MultivaluedMapImpl();
        queryParamsFlights.add("origin", "Copenhagen");
        queryParamsFlights.add("destination", "Berlin");
        queryParamsFlights.add("departureDate", "2016-01-01");
        FlightContainer flights = flightsResource.queryParams(queryParamsFlights).get(new GenericType<FlightContainer>() {
        });

        for (FlightInfo flight : flights.flight) {
            // Prepare Add flight query params.
            MultivaluedMap queryParamsAddFlight = new MultivaluedMapImpl();
            queryParamsAddFlight.add("itineraryId", itineraryId);
            queryParamsAddFlight.add("bookingNumber", flight.getBookingNumber());
            // Add flight.
            ClientResponse response2 = flightsResource.queryParams(queryParamsAddFlight).post(ClientResponse.class);
        }

        // Fetch list itinerary.
        ItineraryContainer list = itineraryResource.path(itineraryId).get(new GenericType<ItineraryContainer>() {
        });
        System.out.print("Done");

        for (Hotel hotel : list.itinerary.hotels) {
            assertEquals("unconfirmed", hotel.status);
        }
        for (Flight flight : list.itinerary.flights) {
            assertEquals("unconfirmed", flight.status);
        }
        assertEquals(1, list.itinerary.flights.size());
        assertEquals(2, list.itinerary.hotels.size());

        // Book.
        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add("name", "Bech Camilla"); // Person with unlimited funds
        formData.add("number", "50408822");
        formData.add("year", "9");
        formData.add("month", "7");
        String response = itineraryResource.path(itineraryId).type(MediaType.APPLICATION_FORM_URLENCODED).put(String.class, formData);

        // Fetch list itinerary.
        list = itineraryResource.path(itineraryId).get(new GenericType<ItineraryContainer>() {
        });

        assertEquals("cancelled", list.itinerary.hotels.get(0).status);
        assertEquals("unconfirmed", list.itinerary.hotels.get(1).status);
        assertEquals("unconfirmed", list.itinerary.flights.get(0).status);
    }
}
