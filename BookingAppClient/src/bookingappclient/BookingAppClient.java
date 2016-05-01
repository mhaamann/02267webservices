/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingappclient;

import java.util.Arrays;

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
        String createItinerary = createItinerary("5");

        System.out.println(createItinerary);
        
        GetHotelsResponse hotels = getHotels("Copenhagen", "2016-01-01", "2016-01-30", "5");
        
        for (Hotel hotel : hotels.getReturn()) {
            System.out.println(hotel.city);
        }
        
        addHotel("7", "5");
        HotelList hotelList = addHotel("12", "5");
        for (HotelReservation reservation : hotelList.getReservation()) {
            System.out.print(reservation.bookingNumber + " - Status:");
            System.out.println(reservation.status);
        }
        
    }

    private static String createItinerary(java.lang.String itineraryId) {
        bookingappclient.Service1 service = new bookingappclient.Service1();
        bookingappclient.BookingServicePortType port = service.getBookingServicePortTypeBindingPort();
        return port.createItinerary(itineraryId);
    }

    private static GetHotelsResponse getHotels(java.lang.String city, java.lang.String arrival, java.lang.String departure, java.lang.String itineraryId) {
        bookingappclient.Service1 service = new bookingappclient.Service1();
        bookingappclient.BookingServicePortType port = service.getBookingServicePortTypeBindingPort();
        return port.getHotels(city, arrival, departure, itineraryId);
    }

    private static HotelList addHotel(java.lang.String bookingNumber, java.lang.String itineraryId) {
        bookingappclient.Service1 service = new bookingappclient.Service1();
        bookingappclient.BookingServicePortType port = service.getBookingServicePortTypeBindingPort();
        return port.addHotel(bookingNumber, itineraryId);
    }

    
}
