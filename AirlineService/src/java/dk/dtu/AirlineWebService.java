/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu;

import dk.dtu.imm.fastmoney.BankService;
import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import dk.dtu.imm.fastmoney.types.AccountType;
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

    //@WebServiceRef(wsdlLocation = "WEB-INF/wsdl/fastmoney.imm.dtu.dk_8080/BankService.wsdl")
    private BankService service;
    FlightInfoDataBase flightDB;
    private static final AccountType account = new AccountType();
    static{
        account.setName("CheapBird");
        account.setNumber("50208813");
    }
    
    public AirlineWebService() throws DatatypeConfigurationException {
        this.flightDB = new FlightInfoDataBase();
    }//end constructor

    @WebMethod(operationName = "getFlights")
    public ArrayList<FlightInfo> getFlights(@WebParam(name = "origin") String origin, 
            @WebParam(name = "destination") String destination, 
            @WebParam(name = "startDate") XMLGregorianCalendar startDate){     
        return flightDB.getFlights(origin, destination, startDate);
    }//end method getFlights
    
    @WebMethod(operationName = "bookFlight")
    public boolean bookFlight(@WebParam(name = "bookingNumber") String bookingNumber, 
            @WebParam(name = "creditCard") dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCard){
        for(FlightInfo flight : flightDB.flightList){
            if(flight.bookingNumber.equals(bookingNumber)){
                //try to charge creditcard if the flight was found
                try{
                    chargeCreditCard(01, creditCard, flight.price, account);
                    return true;
                }catch(CreditCardFaultMessage e){
                }//end try/catch
                //if flight was booked successfully
                //which means that the flight was found and
                //there were sufficient funds on the creditcard
            }else{
                return false;
            }//end if/else
        }//end for
        return false;
    }//end method bookFlight
    
    @WebMethod(operationName = "cancelFlight")
    public boolean cancelFlight(@WebParam(name = "bookingNumber") String bookingNumber, 
            @WebParam(name = "price") int price, 
            @WebParam(name = "creditCard") dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCard){
        for(FlightInfo flight : flightDB.flightList){
            if(flight.bookingNumber.equals(bookingNumber)){
                try{
                    refundCreditCard(1, creditCard, flight.price, account);
                    return true;
                }catch(CreditCardFaultMessage e){
                }//end try/catch
            }else{
                return false;
            }//end if/else
        }//end for
        return false;
    }
    
    private boolean chargeCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, 
            int amount, dk.dtu.imm.fastmoney.types.AccountType account) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.chargeCreditCard(group, creditCardInfo, amount, account);
    }

    private boolean refundCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, 
            int amount, dk.dtu.imm.fastmoney.types.AccountType account) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.refundCreditCard(group, creditCardInfo, amount, account);
    }
    
}
