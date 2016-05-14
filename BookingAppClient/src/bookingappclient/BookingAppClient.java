package bookingappclient;

import ExternalBookingService.Booking;
import ExternalBookingService.FlightInfo;
import ExternalBookingService.FlightList;
import ExternalBookingService.FlightReservation;
import ExternalBookingService.GetFlightsResponse;
import ExternalBookingService.GetHotelsResponse;
import ExternalBookingService.HotelList;
import ExternalBookingService.HotelReservation;
import java.math.BigInteger;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

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
        String itineraryId = "38";
        BookingServiceBPEL bookingServiceBPEL = new BookingServiceBPEL();
        BookingServiceBPELWrapper bsWrapper = new BookingServiceBPELWrapper();

        Random randomGenerator = new Random();
        int random = randomGenerator.nextInt(10000);
        itineraryId = Integer.toString(random);
        
        System.out.println("Create Itinerary number " + itineraryId);
        System.out.println(bsWrapper.createItinerary(itineraryId));
       
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
        
        System.out.println("Adding Hotel..");
        bookingServiceBPEL.addHotel("7", itineraryId);
        HotelList hotelList = bookingServiceBPEL.addHotel("12", itineraryId);
        for (HotelReservation reservation : hotelList.getReservation()) {
            System.out.print(reservation.getBookingNumber() + " - Status:");
            System.out.println(reservation.getStatus());
        }
        
        System.out.println("Adding flight..");
        FlightList flightList = bookingServiceBPEL.addFlight("B12341", itineraryId);
        for (FlightReservation reservation : flightList.getReservation()) {
            System.out.print(reservation.getBookingNumber() + " - Status:");
            System.out.println(reservation.getStatus());
        }
        
        
        System.out.println("Listing..");
        
        List<HotelReservation> hReservationList = bsWrapper.getHotelItineraryList(itineraryId);
        for(HotelReservation reservation : hReservationList){
            System.out.print(reservation.getBookingNumber() + " - Status:");
            System.out.println(reservation.getStatus());
        }
        
        
        List<FlightReservation> fReservationList = bsWrapper.getFlightItineraryList(itineraryId);
        ListIterator<FlightReservation> fIterator = fReservationList.listIterator();
        
        for (FlightReservation reservation : fReservationList){
            System.out.print(reservation.getBookingNumber() + " - Status:");
            System.out.println(reservation.getStatus());
        }
       
        System.out.println("Booking..");
        HotelList hotelListBooked = bookingServiceBPEL.bookItinerary(itineraryId, "Anne Strandberg", "50408816", BigInteger.valueOf(5), BigInteger.valueOf(9));
        for (HotelReservation reservation : hotelListBooked.getReservation()) {
            System.out.print(reservation.getBookingNumber() + " - Status:");
            System.out.println(reservation.getStatus());
        }
        
        System.out.println("Listing..");
        
        hReservationList = bsWrapper.getHotelItineraryList(itineraryId);       
        for (HotelReservation reservation : hReservationList){
            System.out.print(reservation.getBookingNumber() + " - Status:");
            System.out.println(reservation.getStatus());
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
        for (HotelReservation reservation : hReservationList){
            System.out.print(reservation.getBookingNumber() + " - Status:");
            System.out.println(reservation.getStatus());
        }
        
        fReservationList = bsWrapper.getFlightItineraryList(itineraryId);
        for(FlightReservation reservation : fReservationList){
            System.out.print(reservation.getBookingNumber() + " - Status:");
            System.out.println(reservation.getStatus());
        }
    }   
}