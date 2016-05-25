/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.xml;

/**
 *
 * @author Matthias Haamann
 */
public class Action {
    public String method;
    public String uri;
            
    public Action (String method, String uri) {
        this.method = method;
        this.uri = uri;
    }
}
