/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu;


import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author DFS
 */
@WebService(serviceName = "AirlineWebService")
public class AirlineWebService {

    FlightInfoDataBase flightDB;

    public AirlineWebService() throws DatatypeConfigurationException {
        this.flightDB = new FlightInfoDataBase();
    }//end constructor

    @WebMethod(operationName = "getFlights")
    public ArrayList<FlightInfo> getFlights(@WebParam(name = "origin") String origin,
            @WebParam(name = "destination") String destination,
            @WebParam(name = "startDate") String startDate) {
        return flightDB.getFlights(origin, destination, startDate);
    }//end method getFlights

    @WebMethod(operationName = "bookFlight")
    public boolean bookFlight(@WebParam(name = "bookingNumber") String bookingNumber,
            @WebParam(name = "year") int year,
            @WebParam(name = "month") int month,
            @WebParam(name = "number") int number,
            @WebParam(name = "name") String name) {
        /*for (FlightInfo flight : flightDB.flightList) {
            if (flight.bookingNumber.equals(bookingNumber)) {
                //try to charge creditcard if the flight was found
                try {
                    dk.dtu.imm.fastmoney.types.CreditCardInfoType card = null;
                    chargeCreditCard(01, card, flight.price, account);
                    return true;
                } catch (CreditCardFaultMessage e) {
                }//end try/catch
                //if flight was booked successfully
                //which means that the flight was found and
                //there were sufficient funds on the creditcard
            } else {
                return false;
            }//end if/else
        }//end for
        return false;*/
        return true;
    }//end method bookFlight

    @WebMethod(operationName = "cancelFlight")
    public boolean cancelFlight(@WebParam(name = "bookingNumber") String bookingNumber,
            @WebParam(name = "price") int price,
            @WebParam(name = "year") int year,
            @WebParam(name = "month") int month,
            @WebParam(name = "number") int number,
            @WebParam(name = "name") String name) {
        for (FlightInfo flight : flightDB.flightList) {
            if (flight.bookingNumber.equals(bookingNumber)) {
                
                // refundCreditCard(1, creditCard, flight.price, account);
                return true;
                
            } else {
                return false;
            }//end if/else
        }//end for
        return false;
    }

}
