/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exception;

/**
 *
 * @author Patrick
 */
public class ExpirationFormatException extends Exception {
    
    public ExpirationFormatException() {
        
    }
    
    public ExpirationFormatException(String message) {
        super(message);
    }
}
