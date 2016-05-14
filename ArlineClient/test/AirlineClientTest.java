/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import dk.dtu.imm.fastmoney.types.AccountType;
//import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import arlineclient.AirlineServiceWrapper;
import dk.dtu.FlightInfo;
import dk.dtu.ParseException_Exception;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author DFS
 */
public class AirlineClientTest {
    
    String startDate = "2016-01-01";
    String bookingNumberA = "B12341";
    
    @Test
    public void AirlineClientTest1() throws DatatypeConfigurationException, ParseException_Exception {
        List<FlightInfo> result;
        List<FlightInfo> expectedResult = new ArrayList<>();
        
        result = AirlineServiceWrapper.getFlights("Copenhagen", "Berlin", startDate);
        FlightInfo flight = result.get(0);
        assertNotNull(flight);
    }
    
    @Test
    public void AirlineClientTest2() throws DatatypeConfigurationException, ParseException_Exception {
        List<FlightInfo> result;
        result = AirlineServiceWrapper.getFlights("Copenhagen", "Berlin", startDate);
        FlightInfo flight = (FlightInfo)result.get(0);
        bookingNumberA = flight.getBookingNumber();
        boolean isBooked;
        
        isBooked = AirlineServiceWrapper.bookFlight(bookingNumberA, 10, 9, "50408823", "Tobiasen Inge");
        assertEquals(true, isBooked);
    }

    @Test
    public void AirlineClientTest3() {
        boolean result = AirlineServiceWrapper.cancelFlight(bookingNumberA);
        assertEquals(true, result);
    }  

    public AirlineClientTest() {
    }


}
