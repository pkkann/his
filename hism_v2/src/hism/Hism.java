/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hism;

import control.PersonHandler;
import model.PersonRegister;

/**
 *
 * @author patrick
 */
public class Hism {
    
    public Hism() {
        
    }
    
    public static void main(String[] args) {
        PersonRegister peR = new PersonRegister();
        PersonHandler pH = new PersonHandler(peR);
        
        int errorCode = pH.createPerson("Patrick", "", "Kann", "8-56", "21/04/1989", "10/2013", "04/10/2013", "N");
        System.out.println(errorCode);
        System.out.println("----------------------------------------------");
        
        errorCode = pH.savePerson(1, "Duller", "", "Lort", "8-56", "21/04/1989", "10/2013", "N");
        System.out.println(errorCode);
    }
    
}
