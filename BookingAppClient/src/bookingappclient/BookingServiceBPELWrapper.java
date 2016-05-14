package bookingappclient;

import ExternalBookingService.Booking;
import ExternalBookingService.FlightInfo;
import ExternalBookingService.FlightList;
import ExternalBookingService.FlightListType;
import ExternalBookingService.FlightReservation;
import ExternalBookingService.GetFlightsResponse;
import ExternalBookingService.GetHotelsResponse;
import ExternalBookingService.HotelList;
import ExternalBookingService.HotelListType;
import ExternalBookingService.HotelReservation;
import ExternalBookingService.ItineryList;
import bookingappclient.bookingserviceinterface.BookingServiceInterface;
import java.util.List;

/**
 *
 * @author jens
 */
public class BookingServiceBPELWrapper implements BookingServiceInterface{
    BookingServiceBPEL bookingServiceBPEL = new BookingServiceBPEL();
    
    @Override
    public List<HotelReservation> bookItinerary(java.lang.String itineraryId, java.lang.String name, java.math.BigInteger number, java.math.BigInteger month, java.math.BigInteger year) {
        HotelList hotelList = bookingServiceBPEL.bookItinerary(itineraryId, name, number, month, year);
        List<HotelReservation> hotelReservationList = hotelList.getReservation();
        return hotelReservationList;
    }

    @Override
    public String createItinerary(String itineraryId) {
        return bookingServiceBPEL.createItinerary(itineraryId);   
    }

    @Override
    public List<Object> listItinerary(String itineraryId) {
        ItineryList itineryList = bookingServiceBPEL.listItinerary(itineraryId);
        List<Object> objectList = itineryList.getFlightsAndHotels();
        
        return objectList;
    }
    
    @Override
    public List<HotelReservation> getHotelItineraryList(String itineraryId){
        ItineryList itineryList = bookingServiceBPEL.listItinerary(itineraryId);
        List<Object> objectList = itineryList.getFlightsAndHotels();
        HotelListType hList = (HotelListType) objectList.get(1);
        List<HotelReservation> hReservationList = hList.getReservation();
        return hReservationList;
    }
    
    @Override
    public List<FlightReservation> getFlightItineraryList(String itineraryId){
        ItineryList itineryList = bookingServiceBPEL.listItinerary(itineraryId);
        List<Object> objectList = itineryList.getFlightsAndHotels();
        FlightListType fList = (FlightListType) objectList.get(0);
        List<FlightReservation> fReservationList = fList.getReservation();
        return fReservationList;
    }
    
    @Override
    public List<HotelReservation> cancelItinerary(String itineraryId) {
        HotelList hotelList = bookingServiceBPEL.cancelItinerary(itineraryId);
        List<HotelReservation> hotelReservationList = hotelList.getReservation();
        return hotelReservationList;
    }

    @Override
    public List<Booking> getHotels(String city, String arrival, String departure, String itineraryId) {
        GetHotelsResponse getHotelsResponse = bookingServiceBPEL.getHotels(city, arrival, departure, itineraryId);
        List<Booking> bookingList = getHotelsResponse.getReturn();
        return bookingList;
    }

    @Override
    public List<HotelReservation> addHotel(String bookingNumber, String itineraryId) {
        bookingServiceBPEL.addHotel(bookingNumber, itineraryId);
        HotelList hotelList = bookingServiceBPEL.cancelItinerary(itineraryId);
        List<HotelReservation> hotelReservationList = hotelList.getReservation();
        return hotelReservationList;
    }

    @Override
    public List<FlightInfo> getFlights(String origin, String destination, String startDate, String itineraryId) {
        GetFlightsResponse getFlightsResponse = bookingServiceBPEL.getFlights(origin, destination, startDate, itineraryId);
        List<FlightInfo> flightInfoList = getFlightsResponse.getReturn();
        return flightInfoList;
    }
    
    @Override
    public List<FlightReservation> addFlight(java.lang.String bookingNumber, java.lang.String itineraryId){
        FlightList flightList = bookingServiceBPEL.addFlight(bookingNumber, itineraryId);
        List<FlightReservation> flightReservation = flightList.getReservation();
        return flightReservation;
    }
    
}
