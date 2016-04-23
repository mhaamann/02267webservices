/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concat.ws;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author jens
 */
@WebService(serviceName = "ConcatWebService")
public class ConcatWebService {

    /**
     * This is a sample web service operation
     */
    
    public String hello(String input1, String input2) {
        return input1 + input2;
    }
}
