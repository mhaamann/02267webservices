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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jens
 */
public class BookingServiceBPELWrapper implements BookingServiceInterface{
    BookingServiceBPEL bookingServiceBPEL = new BookingServiceBPEL();
    
    @Override
    public String bookItinerary(java.lang.String itineraryId, java.lang.String name, java.lang.String number, java.math.BigInteger month, java.math.BigInteger year) {
        String answer = bookingServiceBPEL.bookItinerary(itineraryId, name, number, month, year);
        return answer;
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
        List<HotelReservation> hReservationList = new ArrayList<>();
        HotelListType hList;
        for(Object object : objectList){
            if(HotelListType.class.isInstance(object)){
                hList = (HotelListType) object;
                hReservationList = hList.getReservation();
            }           
        }
        return hReservationList;
    }
    
    @Override
    public List<FlightReservation> getFlightItineraryList(String itineraryId){
        ItineryList itineryList = bookingServiceBPEL.listItinerary(itineraryId);
        List<Object> objectList = itineryList.getFlightsAndHotels();
        List<FlightReservation> fReservationList = new ArrayList<>();
        FlightListType fList;
        for(Object object : objectList){
            if(FlightListType.class.isInstance(object)){
                fList = (FlightListType) object;
                fReservationList = fList.getReservation();
            }           
        }
        return fReservationList;
    }
    
    @Override
    public String cancelItinerary(String itineraryId) {
        String status = bookingServiceBPEL.cancelItinerary(itineraryId);
        return status;
    }

    @Override
    public List<Booking> getHotels(String city, String arrival, String departure, String itineraryId) {
        GetHotelsResponse getHotelsResponse = bookingServiceBPEL.getHotels(city, arrival, departure, itineraryId);
        List<Booking> bookingList = getHotelsResponse.getReturn();
        return bookingList;
    }

    @Override
    public List<HotelReservation> addHotel(String bookingNumber, String itineraryId) {
        HotelList hotelList = bookingServiceBPEL.addHotel(bookingNumber, itineraryId);
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
    
    @Override
    public boolean reset(java.lang.String itineraryId){
        return bookingServiceBPEL.reset(itineraryId);
    }
}
