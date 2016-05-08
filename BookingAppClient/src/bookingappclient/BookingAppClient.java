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
import java.util.Arrays;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.Dispatch;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Service;
import java.io.StringReader;
import java.math.BigInteger;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

/**
 *
 * @author jens
 */
public class BookingAppClient {
    
    // TODO: BPEL proccess
    // Add airline service, get, add, cancel and book
    // merge lists when calling get itinerary
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Starting");
        String itineraryId = "13";
        BookingServiceBPEL bookingServiceBPEL = new BookingServiceBPEL();
        BookingServiceBPELWrapper bsWrapper = new BookingServiceBPELWrapper();


        System.out.println(BookingService.createItinerary(itineraryId));
       
        System.out.println("Finding hotels..");
        GetHotelsResponse hotels = bookingServiceBPEL.getHotels("Copenhagen", "2016-01-01", "2016-01-30", itineraryId);
        for (Booking booking : hotels.getReturn()) {
            System.out.println(booking.getCity());
            System.out.println(booking.getTotalPrice());
        }
        
        System.out.println("Finding Flights..");
        GetFlightsResponse flights = bookingServiceBPEL.getFlights("Copenhagen", "Berlin", "2016-01-01", itineraryId);
        for (FlightInfo flight : flights.getReturn()) {
            System.out.println(flight.getOrigin() + " - " + flight.getPrice() + "Booking ID: " + flight.getBookingNumber());
        }
        
        System.out.println("Adding..");
        bookingServiceBPEL.addHotel("7", itineraryId);
        HotelList hotelList = bookingServiceBPEL.addHotel("12", itineraryId);
        for (HotelReservation reservation : hotelList.getReservation()) {
            System.out.print(reservation.getBookingNumber() + " - Status:");
            System.out.println(reservation.getStatus());
        }
        
        System.out.println("Adding flight..");
        FlightList flightList = BookingServiceBPEL.addFlight("B12341", itineraryId);
        for (FlightReservation reservation : flightList.getReservation()) {
            System.out.print(reservation.getBookingNumber() + " - Status:");
            System.out.println(reservation.getStatus());
        }
        
        
        System.out.println("Listing..");
        
        List<HotelReservation> hReservationList = bsWrapper.getHotelItineraryList(itineraryId);       
        ListIterator<HotelReservation> hIterator = hReservationList.listIterator();
        while(hIterator.hasNext()){
            HotelReservation hReservation = hIterator.next();
            System.out.print(hReservation.getBookingNumber() + " - Status:");
            System.out.println(hReservation.getStatus());
        }
        
        List<FlightReservation> fReservationList = bsWrapper.getFlightItineraryList(itineraryId);
        ListIterator<FlightReservation> fIterator = fReservationList.listIterator();
        while(fIterator.hasNext()){
            FlightReservation fReservation = fIterator.next();
            System.out.print(fReservation.getBookingNumber() + " - Status:");
            System.out.println(fReservation.getStatus());
        }
       
        System.out.println("Booking..");
        HotelList hotelListBooked = bookingServiceBPEL.bookItinerary(itineraryId, "Anne Strandberg", BigInteger.valueOf(50408816), BigInteger.valueOf(5), BigInteger.valueOf(9));
        for (HotelReservation reservation : hotelListBooked.getReservation()) {
            System.out.print(reservation.getBookingNumber() + " - Status:");
            System.out.println(reservation.getStatus());
        }
        
        System.out.println("Listing..");
        
        hReservationList = bsWrapper.getHotelItineraryList(itineraryId);       
        hIterator = hReservationList.listIterator();
        while(hIterator.hasNext()){
            HotelReservation hReservation = hIterator.next();
            System.out.print(hReservation.getBookingNumber() + " - Status:");
            System.out.println(hReservation.getStatus());
        }
        
        fReservationList = bsWrapper.getFlightItineraryList(itineraryId);
        fIterator = fReservationList.listIterator();
        while(fIterator.hasNext()){
            FlightReservation fReservation = fIterator.next();
            System.out.print(fReservation.getBookingNumber() + " - Status:");
            System.out.println(fReservation.getStatus());
        }
        
        System.out.println("Cancelling..");
        HotelList list3 = bookingServiceBPEL.cancelItinerary(itineraryId);
        for (HotelReservation reservation : list3.getReservation()) {
            System.out.print(reservation.getBookingNumber() + " - Status:");
            System.out.println(reservation.getStatus());
        }
        
        System.out.println("Listing..");
        hReservationList = bsWrapper.getHotelItineraryList(itineraryId);       
        hIterator = hReservationList.listIterator();
        while(hIterator.hasNext()){
            HotelReservation hReservation = hIterator.next();
            System.out.print(hReservation.getBookingNumber() + " - Status:");
            System.out.println(hReservation.getStatus());
        }
        
        fReservationList = bsWrapper.getFlightItineraryList(itineraryId);
        fIterator = fReservationList.listIterator();
        while(fIterator.hasNext()){
            FlightReservation fReservation = fIterator.next();
            System.out.print(fReservation.getBookingNumber() + " - Status:");
            System.out.println(fReservation.getStatus());
        }
    }   
}