/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dk.dtu.CreditCard;
import dk.dtu.FlightInfo;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author DFS
 */
public class AirlineClientTest {
    
    @Test
    public void AirlineClientTest1() {
        String result = hello("Me");
        assertEquals("Hello Me ! from Airline", result);
    }
    
    @Test
    public void AirlineClientTest2() {
        CreditCard cc =  new CreditCard();
        boolean result = bookFlight("Me", cc);
        assertEquals(true, result);
    }
    
    @Test
    public void AirlineClientTest3() {
        CreditCard cc =  new CreditCard();
        boolean result = cancelFlight("Blah", 1234, cc);
        assertEquals(true, result);
    }
    
    @Test
    public void AirlineClientTest4() throws DatatypeConfigurationException {
        GregorianCalendar sd = new GregorianCalendar(2014, 10, 8);
        XMLGregorianCalendar startDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(sd);
        List<FlightInfo> result;
        List<FlightInfo> expectedResult = new ArrayList<FlightInfo>();
        
        result = getFlights("Copenhagen", "Berlin", startDate);
        assertEquals(expectedResult, result);
    }
    
    public AirlineClientTest() {
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    private static String hello(java.lang.String name) {
        dk.dtu.AirlineWebService_Service service = new dk.dtu.AirlineWebService_Service();
        dk.dtu.AirlineWebService port = service.getAirlineWebServicePort();
        return port.hello(name);
    }

    private static boolean bookFlight(java.lang.String bookingNumber, dk.dtu.CreditCard creditCard) {
        dk.dtu.AirlineWebService_Service service = new dk.dtu.AirlineWebService_Service();
        dk.dtu.AirlineWebService port = service.getAirlineWebServicePort();
        return port.bookFlight(bookingNumber, creditCard);
    }

    private static boolean cancelFlight(java.lang.String bookingNumber, java.lang.Integer price, dk.dtu.CreditCard creditCard) {
        dk.dtu.AirlineWebService_Service service = new dk.dtu.AirlineWebService_Service();
        dk.dtu.AirlineWebService port = service.getAirlineWebServicePort();
        return port.cancelFlight(bookingNumber, price, creditCard);
    }

    private static java.util.List<dk.dtu.FlightInfo> getFlights(java.lang.String origin, java.lang.String destination, java.lang.Object startDate) {
        dk.dtu.AirlineWebService_Service service = new dk.dtu.AirlineWebService_Service();
        dk.dtu.AirlineWebService port = service.getAirlineWebServicePort();
        return port.getFlights(origin, destination, startDate);
    }
}
