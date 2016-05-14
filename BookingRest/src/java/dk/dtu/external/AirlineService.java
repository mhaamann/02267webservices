/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.external;

import dk.dtu.CreditCardFaultMessage;
import dk.dtu.ParseException_Exception;

/**
 *
 * @author mhaamann
 */
public class AirlineService {

    public static boolean bookFlight(java.lang.String bookingNumber, int year, int month, java.lang.String number, java.lang.String name) {
        dk.dtu.AirlineWebService_Service service = new dk.dtu.AirlineWebService_Service();
        dk.dtu.AirlineWebService port = service.getAirlineWebServicePort();
        return port.bookFlight(bookingNumber, year, month, number, name);
    }

    public static boolean cancelFlight(java.lang.String bookingNumber) throws CreditCardFaultMessage {
        dk.dtu.AirlineWebService_Service service = new dk.dtu.AirlineWebService_Service();
        dk.dtu.AirlineWebService port = service.getAirlineWebServicePort();
        return port.cancelFlight(bookingNumber);
    }

    public static java.util.List<dk.dtu.FlightInfo> getFlights(java.lang.String origin, java.lang.String destination, java.lang.String startDate) throws ParseException_Exception {
        dk.dtu.AirlineWebService_Service service = new dk.dtu.AirlineWebService_Service();
        dk.dtu.AirlineWebService port = service.getAirlineWebServicePort();
        return port.getFlights(origin, destination, startDate);
    }

    public static boolean resetAirline() {
        dk.dtu.AirlineWebService_Service service = new dk.dtu.AirlineWebService_Service();
        dk.dtu.AirlineWebService port = service.getAirlineWebServicePort();
        return port.resetAirline();
    }
    
}
