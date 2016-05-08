package bookingappclient.bookingserviceinterface;

import ExternalBookingService.Booking;
import ExternalBookingService.FlightInfo;
import ExternalBookingService.HotelReservation;
import ExternalBookingService.GetFlightsResponse;
import ExternalBookingService.GetHotelsResponse;
import ExternalBookingService.HotelList;
import java.util.List;

/**
 *
 * @author jens
 */
public interface BookingServiceInterface {
    // Itinerary operations - Start
    
    public List<HotelReservation> bookItinerary(java.lang.String itineraryId, java.lang.String name, java.math.BigInteger number, java.math.BigInteger month, java.math.BigInteger year);

    public String createItinerary(java.lang.String itineraryId);
    
    public List<HotelReservation>  listItinerary(java.lang.String itineraryId);

    public List<HotelReservation>  cancelItinerary(java.lang.String itineraryId);
    
    // Itinerary operations - End
    
    // Hotel operations - Start

    public List<Booking> getHotels(java.lang.String city, java.lang.String arrival, java.lang.String departure, java.lang.String itineraryId) ;
    
    public List<HotelReservation> addHotel(java.lang.String bookingNumber, java.lang.String itineraryId);
    
    // Hotel operations - End
    
    // Flight operations - Start

    public List<FlightInfo> getFlights(java.lang.String origin, java.lang.String destination, java.lang.String startDate, java.lang.String itineraryId);
    
    // Flight operations - End
}
