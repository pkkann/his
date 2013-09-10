/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import login.LoginHandler;
import view.*;

/**
 *
 * @author patrick
 */
public class HISM {
    
    private LoginHandler loginHandler;
    
    private static MainGUI mainGUI;
    private static LoginDIA loginDIA;
    
    public HISM() {
        loginDIA = new LoginDIA(mainGUI, true, loginHandler);
        loginHandler = new LoginHandler(loginDIA);
        
        mainGUI = new MainGUI(loginHandler);
        
    }
    
    public static void main(String[] args) {
        HISM hism = new HISM();
    }
    
}
