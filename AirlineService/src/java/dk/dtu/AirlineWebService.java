/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu;

import dk.dtu.BankService.AccountType;
import dk.dtu.BankService.BankService;
import dk.dtu.BankService.CreditCardFaultMessage;
import dk.dtu.BankService.CreditCardInfoType;
import dk.dtu.BankService.CreditCardInfoType.ExpirationDate;
import java.text.ParseException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.ws.WebServiceRef;


/**
 *
 * @author DFS
 */
@WebService(serviceName = "AirlineWebService")
public class AirlineWebService {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/fastmoney.imm.dtu.dk_8080/BankService.wsdl")
    
    ArrayList<Payment> payments = new ArrayList<Payment>();
    
    private BankService service;

    FlightInfoDataBase flightDB;

    public AirlineWebService() throws DatatypeConfigurationException {
        this.flightDB = new FlightInfoDataBase();
    }//end constructor

    @WebMethod(operationName = "getFlights")
    public ArrayList<FlightInfo> getFlights(@WebParam(name = "origin") String origin,
            @WebParam(name = "destination") String destination,
            @WebParam(name = "startDate") String startDate) throws ParseException {
        return flightDB.getFlights(origin, destination, startDate);
    }//end method getFlights
    
    @WebMethod(operationName = "resetAirline")
    public boolean resetAirline() {
        return true;
    }
    
    
    AccountType account = new AccountType();
    ExpirationDate expDate = new CreditCardInfoType.ExpirationDate();
    CreditCardInfoType creditCard = new CreditCardInfoType();
    
    ArrayList<String> parameterList = new ArrayList<>();
    String parameters = "";
    @WebMethod(operationName = "bookFlight")
    public boolean bookFlight(@WebParam(name = "bookingNumber") String bookingNumber,
            @WebParam(name = "year") int year,
            @WebParam(name = "month") int month,
            @WebParam(name = "number") String number,
            @WebParam(name = "name") String name) {
        
        payments.add(new Payment(bookingNumber, year, month, number, name));
        //parameters = bookingNumber + "#!" + String.valueOf(year) +
        //        "#!" + String.valueOf(month) + "#!" + number + "#!" + name;
        //parameterList.add(parameters);
        
        //dk.dtu.BankService.AccountType account = new dk.dtu.BankService.AccountType();
        account.setName("LameDuck");
        account.setNumber("50208812");
        
        //dk.dtu.BankService.CreditCardInfoType.ExpirationDate expDate = new dk.dtu.BankService.CreditCardInfoType.ExpirationDate();
        expDate.setMonth(month);
        expDate.setYear(year);
        
       // dk.dtu.BankService.CreditCardInfoType creditCard = new dk.dtu.BankService.CreditCardInfoType();
        creditCard.setExpirationDate(expDate);
        creditCard.setName(name);
        creditCard.setNumber(String.valueOf(number));
        
        for (FlightInfo flight : flightDB.flightList) {
            if (flight.bookingNumber.equals(bookingNumber)) {
                //try to charge creditcard if the flight was found
                try {
                    chargeCreditCard(1, creditCard, flight.price, account);
                    return true;
                } catch (CreditCardFaultMessage e) {
                }//end try/catch
                //if flight was booked successfully
                //which means that the flight was found and
                //there were sufficient funds on the creditcard
            }
        }//end for
        return false;
        //return true;
    }//end method bookFlight

    @WebMethod(operationName = "cancelFlight")
    public boolean cancelFlight(@WebParam(name = "bookingNumber") String bookingNumber) throws CreditCardFaultMessage, Exception {
        
        Payment foundPayment = null;
        for (Payment payment : payments) {
            if (payment.bookingNumber.equals(bookingNumber)) {
                foundPayment = payment;
                break;
            }
        }
        
        if (foundPayment == null) {
            throw new Exception("Payment could not be found, abort!");
        }
        /*String[] params = new String[5];
            for(String s : parameterList){
                params = s.split("#!");
                if(params[0].equals(bookingNumber)){
                    break;
                }
            }*/
        expDate.setMonth(Integer.valueOf(foundPayment.month));
        expDate.setYear(Integer.valueOf(foundPayment.year));
        creditCard.setExpirationDate(expDate);
        creditCard.setName(foundPayment.name);
        creditCard.setNumber(foundPayment.number);
       
        for (FlightInfo flight : flightDB.flightList) {    
            if (flight.bookingNumber.equals(bookingNumber)) {
                refundCreditCard(1, creditCard, flight.price, account);
                return true;      
            }
        }//end for
        return false;
    }

    private boolean chargeCreditCard(int group, dk.dtu.BankService.CreditCardInfoType creditCardInfo, int amount, dk.dtu.BankService.AccountType account) throws CreditCardFaultMessage {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        dk.dtu.BankService.BankPortType port = service.getBankPort();
        return port.chargeCreditCard(group, creditCardInfo, amount, account);
    }

    private boolean refundCreditCard(int group, dk.dtu.BankService.CreditCardInfoType creditCardInfo, int amount, dk.dtu.BankService.AccountType account) throws CreditCardFaultMessage {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        dk.dtu.BankService.BankPortType port = service.getBankPort();
        return port.refundCreditCard(group, creditCardInfo, amount, account);
    }
}
