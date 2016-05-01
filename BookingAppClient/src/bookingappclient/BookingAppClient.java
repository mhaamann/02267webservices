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
        String createItinerary = createItinerary("2");
        
        System.out.println(createItinerary);
        
        GetHotelsResponse hotels = getHotels("Copenhagen", "2016-01-01", "2016-01-30", "2");
        
        for (Hotel hotel : hotels.getReturn()) {
            System.out.println(hotel.city);
        }
        
    }

    private static String createItinerary(java.lang.String customerId) {
        bookingappclient.Service1 service = new bookingappclient.Service1();
        bookingappclient.BookingServicePortType port = service.getBookingServicePortTypeBindingPort();
        return port.createItinerary(customerId);
    }

    private static GetHotelsResponse getHotels(java.lang.String city, java.lang.String arrival, java.lang.String departure, java.lang.String customerId) {
        bookingappclient.Service1 service = new bookingappclient.Service1();
        bookingappclient.BookingServicePortType port = service.getBookingServicePortTypeBindingPort();
        return port.getHotels(city, arrival, departure, customerId);
    }


    
}
