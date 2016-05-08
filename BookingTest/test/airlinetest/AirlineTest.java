package airlinetest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dk.dtu.ParseException_Exception;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class AirlineTest {
    
    public AirlineTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    private static boolean bookFlight(java.lang.String bookingNumber, int year, int month, int number, java.lang.String name) {
        dk.dtu.AirlineWebService_Service service = new dk.dtu.AirlineWebService_Service();
        dk.dtu.AirlineWebService port = service.getAirlineWebServicePort();
        return port.bookFlight(bookingNumber, year, month, number, name);
    }

    private static boolean cancelFlight(java.lang.String bookingNumber) {
        dk.dtu.AirlineWebService_Service service = new dk.dtu.AirlineWebService_Service();
        dk.dtu.AirlineWebService port = service.getAirlineWebServicePort();
        return port.cancelFlight(bookingNumber);
    }

    private static java.util.List<dk.dtu.FlightInfo> getFlights(java.lang.String origin, java.lang.String destination, java.lang.String startDate) throws ParseException_Exception {
        dk.dtu.AirlineWebService_Service service = new dk.dtu.AirlineWebService_Service();
        dk.dtu.AirlineWebService port = service.getAirlineWebServicePort();
        return port.getFlights(origin, destination, startDate);
    }
}
