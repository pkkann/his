/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hism;

import control.EnrollmentHandler;
import control.PersonHandler;
import control.UserHandler;
import model.EnrollmentRegister;
import model.PersonRegister;
import model.UserRegister;

/**
 *
 * @author patrick
 */
public class Hism {
    
    public Hism() {
        
    }
    
    public static void main(String[] args) {
        PersonRegister peR = new PersonRegister();
        PersonHandler peH = new PersonHandler(peR);
        UserRegister usR = new UserRegister();
        UserHandler usH = new UserHandler(usR);
        EnrollmentRegister enR = new EnrollmentRegister();
        EnrollmentHandler enH = new EnrollmentHandler(enR, peR, usR);
        
        int errorCode = peH.createPerson("Patrick", "", "Kann", "8-56", "21/04/1989", "10/2013", "04/10/2013", false, false, "N");
        System.out.println(errorCode);
        
        errorCode = usH.createUser("pkkann", "Rollercoaster2", "Patrick", "", "Kann", "04/10/2013", false, false);
        System.out.println(errorCode);
        
        errorCode = enH.createEnrollment(1, 1);
        System.out.println(errorCode);
        
        errorCode = enH.createGuest(1, 1, "Lars", "", "Kann", "21/04/1989", "05/10/2013");
        System.out.println(errorCode);
        
        enH.removeGuest(1, 1);
        
    }
    
}
