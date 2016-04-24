/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingappclient;

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
        
        String createItinerary = createItinerary();
        
        System.out.println(createItinerary);
        
        GetHotelsResponse hotels = getHotels("Copenhagen", "2016-01-01", "2016-01-30");
        
        
        System.out.println(hotels);
        
    }

    private static String createItinerary() {
        bookingappclient.Service1 service = new bookingappclient.Service1();
        bookingappclient.BookingServicePortType port = service.getBookingServicePortTypeBindingPort();
        return port.createItinerary();
    }

    private static GetHotelsResponse getHotels(java.lang.String city, java.lang.String arrival, java.lang.String departure) {
        bookingappclient.Service1 service = new bookingappclient.Service1();
        bookingappclient.BookingServicePortType port = service.getBookingServicePortTypeBindingPort();
        return port.getHotels(city, arrival, departure);
    }
    
}
