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
public class AirlineClientTest {
    
    @Test
    public void AirlineClientTest1() {
        String result = hello("Me");
        assertEquals("Hello Me !", result);
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
}
