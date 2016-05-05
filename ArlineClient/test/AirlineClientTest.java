/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import dk.dtu.imm.fastmoney.types.AccountType;
//import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType.ExpirationDate;
import dk.dtu.FlightInfo;
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
    /*
    static class CreditCardInfoType{
        String name;
        String number;
        ExpirationDate ed;
        
        public void setName(String name){
            this.name = name;
        }
        public void setNumber(String num){
            this.number = num;
        }
        
        public void setExpirationDate(ExpirationDate exD){
            this.ed.setMonth(exD.getMonth());
            this.ed.setYear(exD.getYear());
        }
        
    }
    
    static class AccountType{
        String name;
        String number;
        
        public void setName(String name){
            this.name = name;
        }
        public void setNumber(String num){
            this.number = num;
        }
    }
    */
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
    /*
    private static final dk.dtu.imm.fastmoney.types.AccountType account = new dk.dtu.imm.fastmoney.types.AccountType();
    static{
        account.setName("CheapBird");
        account.setNumber("50208813");
    }
  */  
    @Test
    public void AirlineClientTest1() throws DatatypeConfigurationException {
        GregorianCalendar sd = new GregorianCalendar(2014, 10, 8);
        XMLGregorianCalendar startDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(sd);
        List<FlightInfo> result;
        List<FlightInfo> expectedResult = new ArrayList<>();
        
        result = getFlights("Copenhagen", "Berlin", startDate);
        FlightInfo flight = result.get(0);
        assertNotNull(flight);
    }
    
    @Test
    public void AirlineClientTest2() throws DatatypeConfigurationException {
        GregorianCalendar sd = new GregorianCalendar(2014, 10, 8);
        XMLGregorianCalendar startDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(sd);
        List<FlightInfo> result;
        
        result = getFlights("Copenhagen", "Berlin", startDate);
        FlightInfo flight = (FlightInfo)result.get(0);
        
        boolean isBooked;
        
        isBooked = bookFlight("B12341", creditCard);
        assertEquals(true, isBooked);
    }

 /*   

    @Test
    public void AirlineClientTest3() {
        CreditCard cc =  new CreditCard();
        boolean result = cancelFlight("Blah", 1234, cc);
        assertEquals(true, result);
    }

   */    

    public AirlineClientTest() {
    }
    private static boolean bookFlight(java.lang.String bookingNumber, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCard) {
        dk.dtu.AirlineWebService_Service service = new dk.dtu.AirlineWebService_Service();
        dk.dtu.AirlineWebService port = service.getAirlineWebServicePort();
        
        //return true;
        return port.bookFlight(bookingNumber, creditCard);
    }

    private static boolean cancelFlight(java.lang.String bookingNumber, java.lang.Integer price, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCard) {
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
