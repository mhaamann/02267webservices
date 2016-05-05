/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingappclient;

import ExternalBookingService.GetHotelsResponse;
import ExternalBookingService.Hotel;
import ExternalBookingService.HotelList;
import ExternalBookingService.HotelReservation;
import java.util.Arrays;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.Dispatch;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Service;
import java.io.StringReader;
import java.math.BigInteger;

/**
 *
 * @author jens
 */
public class BookingAppClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Starting");
        String itinerary = "7";
        String createItinerary = createItinerary(itinerary);

        System.out.println(createItinerary);
        
        GetHotelsResponse hotels = getHotels("Copenhagen", "2016-01-01", "2016-01-30", itinerary);
        
        for (Hotel hotel : hotels.getReturn()) {
            System.out.println(hotel.getCity());
        }
        
        addHotel("7", itinerary);
        HotelList hotelList = addHotel("12", itinerary);
        for (HotelReservation reservation : hotelList.getReservation()) {
            System.out.print(reservation.getBookingNumber() + " - Status:");
            System.out.println(reservation.getStatus());
        }
        
        bookItinerary(itinerary, "Anne Strandberg", BigInteger.valueOf(50408816), BigInteger.valueOf(5), BigInteger.valueOf(9));
        
    }

    private static HotelList addHotel(java.lang.String bookingNumber, java.lang.String itineraryId) {
        ExternalBookingService.Service1 service = new ExternalBookingService.Service1();
        ExternalBookingService.BookingServicePortType port = service.getBookingServicePortTypeBindingPort();
        return port.addHotel(bookingNumber, itineraryId);
    }

    private static HotelList bookItinerary(java.lang.String itineraryId, java.lang.String name, java.math.BigInteger number, java.math.BigInteger month, java.math.BigInteger year) {
        ExternalBookingService.Service1 service = new ExternalBookingService.Service1();
        ExternalBookingService.BookingServicePortType port = service.getBookingServicePortTypeBindingPort();
        return port.bookItinerary(itineraryId, name, number, month, year);
    }

    private static String createItinerary(java.lang.String itineraryId) {
        ExternalBookingService.Service1 service = new ExternalBookingService.Service1();
        ExternalBookingService.BookingServicePortType port = service.getBookingServicePortTypeBindingPort();
        return port.createItinerary(itineraryId);
    }

    private static GetHotelsResponse getHotels(java.lang.String city, java.lang.String arrival, java.lang.String departure, java.lang.String itineraryId) {
        ExternalBookingService.Service1 service = new ExternalBookingService.Service1();
        ExternalBookingService.BookingServicePortType port = service.getBookingServicePortTypeBindingPort();
        return port.getHotels(city, arrival, departure, itineraryId);
    }

   
    

    
}
