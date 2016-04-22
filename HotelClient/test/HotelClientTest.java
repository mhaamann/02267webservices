/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class HotelClientTest {
    
    public HotelClientTest() {
    }
    
    @Test
    public void sayHi(){
        String result = hello("Me");
        assertEquals("Hello Me ! from Hotel", result);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    private static String hello(java.lang.String name) {
        dk.dtu.HotelWebService_Service service = new dk.dtu.HotelWebService_Service();
        dk.dtu.HotelWebService port = service.getHotelWebServicePort();
        return port.hello(name);
    }
}
