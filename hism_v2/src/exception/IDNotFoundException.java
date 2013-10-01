/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author patrick
 */
public class IDNotFoundException extends Exception{
    
    public IDNotFoundException() {
        super("ID was not found");
    }
}
