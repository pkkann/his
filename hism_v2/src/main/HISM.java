/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import control.user.UserHandler;
import control.user.UserRegister;
import javax.swing.JFrame;
import login.LoginHandler;
import view.*;
import view.user.CreateUserDIA;

/**
 *
 * @author patrick
 */
public class HISM {

    private UserRegister userRegister;
    private UserHandler userHandler;
    private LoginHandler loginHandler;
    private static MainGUI mainGUI;
    private static LoginDIA loginDIA;
    private CreateUserDIA createUserDIA;

    public HISM() {
        userRegister = new UserRegister();
        userHandler = new UserHandler();
        
        testData();

        loginHandler = new LoginHandler(loginDIA, userHandler, mainGUI);
        createUserDIA = new CreateUserDIA(mainGUI, true, userHandler);
        mainGUI = new MainGUI(loginHandler, createUserDIA);
    }

    public static void main(String[] args) {
        HISM hism = new HISM();

        hism.loginHandler.requestLoginView();
        mainGUI.setUser();
        mainGUI.setVisible(true);
    }

    private void testData() {
        userHandler.createUser("pkkann@gmail.com", "rollercoaster", "Patrick", "", "Kann", "10092013", false, false);
        userHandler.createUser("lk@nordicnetworking.com", "test", "test", "", "test", "test", true, false);
    }
}
