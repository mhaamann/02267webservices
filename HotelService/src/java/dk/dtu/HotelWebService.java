/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu;

import dk.dtu.imm.fastmoney.BankService;
import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author User
 */
@WebService(serviceName = "HotelWebService")
public class HotelWebService {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/fastmoney.imm.dtu.dk_8080/BankService.wsdl")
    private BankService service;
    private HotelsDB hotelsDB = new HotelsDB();

    @WebMethod(operationName = "getHotels")
    public ArrayList<Hotel> getHotels(@WebParam(name = "city") String city, @WebParam(name = "arrivalDate") String arrivalDate, @WebParam(name = "departureDate") String departureDate, @WebParam(name = "itineraryId") String itineraryId) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // TODO: Return booking number on each hotel.
        return hotelsDB.getHotels(city, dateFormat.parse(arrivalDate), dateFormat.parse(departureDate));
    }

    @WebMethod(operationName = "bookHotel")
    public boolean bookHotel(
            @WebParam(name = "bookingNumber") String bookingNumber,
            @WebParam(name = "year") int year,
            @WebParam(name = "month") int month,
            @WebParam(name = "number") int number,
            @WebParam(name = "name") String name
    ) throws Exception {
        // TODO: When booking a hotel, save the credit card information to be able to refund the money
        return true;
        // throw new Exception("Not enough money");
    }

    @WebMethod(operationName = "cancelHotel")
    public boolean cancelHotel(@WebParam(name = "bookingNumber") String bookingNumber) {
        return true;
    }

    private boolean chargeCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount, dk.dtu.imm.fastmoney.types.AccountType account) throws CreditCardFaultMessage {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.chargeCreditCard(group, creditCardInfo, amount, account);
    }
}
