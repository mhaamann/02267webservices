package airlinetest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dk.dtu.imm.fastmoney.types.CreditCardInfoType.ExpirationDate;
import dk.dtu.FlightInfo;
import dk.dtu.ParseException_Exception;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.junit.Test;
import org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author User
 */
public class AirlineTest {
    
    public static final dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCard = new dk.dtu.imm.fastmoney.types.CreditCardInfoType();
    static{
        //Creditcard has credit of 1000
        creditCard.setName("Tobiasen Inge");
        creditCard.setNumber("50408823");
        ExpirationDate exDate = new ExpirationDate();
        exDate.setMonth(9);
        exDate.setYear(10);
        creditCard.setExpirationDate(exDate);
        
    }
    
    String startDate = "2016-01-01";
    @Test
    public void AirlineClientTest1() throws DatatypeConfigurationException, ParseException_Exception {
        List<FlightInfo> result;
        List<FlightInfo> expectedResult = new ArrayList<>();
        
        result = getFlights("Copenhagen", "Berlin", startDate);
        FlightInfo flight = result.get(0);
        assertNotNull(flight);
    }
    
    @Test
    public void AirlineClientTest2() throws DatatypeConfigurationException, ParseException_Exception {
        List<FlightInfo> result;
        result = getFlights("Copenhagen", "Berlin", startDate);
        FlightInfo flight = (FlightInfo)result.get(0);
        
        boolean isBooked;
        
        isBooked = bookFlight("B12341", creditCard.getExpirationDate().getYear(),
                creditCard.getExpirationDate().getMonth(),
                Integer.parseInt(creditCard.getNumber()), creditCard.getName());//int,int,int,String
        assertEquals(true, isBooked);
    }

    @Test
    public void AirlineClientTest3() {
        boolean result = cancelFlight("B12341");
        assertEquals(true, result);
    }  

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
