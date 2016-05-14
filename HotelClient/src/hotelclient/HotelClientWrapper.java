/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelclient;

import dk.dtu.Exception_Exception;
import dk.dtu.ParseException_Exception;

/**
 *
 * @author mhaamann
 */
public class HotelClientWrapper {

    public static boolean bookHotel(java.lang.String bookingNumber, int year, int month, java.lang.String number, java.lang.String name) throws Exception_Exception {
        dk.dtu.HotelWebService_Service service = new dk.dtu.HotelWebService_Service();
        dk.dtu.HotelWebService port = service.getHotelWebServicePort();
        return port.bookHotel(bookingNumber, year, month, number, name);
    }

    public static boolean cancelHotel(java.lang.String bookingNumber) throws Exception_Exception {
        dk.dtu.HotelWebService_Service service = new dk.dtu.HotelWebService_Service();
        dk.dtu.HotelWebService port = service.getHotelWebServicePort();
        return port.cancelHotel(bookingNumber);
    }

    public static java.util.List<dk.dtu.Booking> getHotels(java.lang.String city, java.lang.String arrivalDate, java.lang.String departureDate) throws ParseException_Exception {
        dk.dtu.HotelWebService_Service service = new dk.dtu.HotelWebService_Service();
        dk.dtu.HotelWebService port = service.getHotelWebServicePort();
        return port.getHotels(city, arrivalDate, departureDate);
    }

    public static boolean resetHotel() {
        dk.dtu.HotelWebService_Service service = new dk.dtu.HotelWebService_Service();
        dk.dtu.HotelWebService port = service.getHotelWebServicePort();
        return port.resetHotel();
    }
    
}
