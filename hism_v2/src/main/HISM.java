/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import control.person.PersonHandler;
import control.person.PersonRegister;
import control.user.UserHandler;
import control.user.UserRegister;
import javax.swing.JOptionPane;
import login.LoginHandler;
import model.User;
import view.*;
import view.person.CreatePersonDIA;
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
    private CreatePersonDIA createPersonDIA;
    private PersonRegister personRegister;
    private PersonHandler personHandler;

    public HISM() {
        personRegister = new PersonRegister();
        personHandler = new PersonHandler(personRegister);
        
        userRegister = new UserRegister();
        userHandler = new UserHandler();
        
        loginHandler = new LoginHandler(loginDIA, userHandler, mainGUI);
        createPersonDIA = new CreatePersonDIA(mainGUI, true, personHandler, loginHandler);
        createUserDIA = new CreateUserDIA(mainGUI, true, userHandler);
        mainGUI = new MainGUI(loginHandler, personHandler, createUserDIA, createPersonDIA);
    }

    public static void main(String[] args) {
        HISM hism = new HISM();
        hism.firstStart();
        hism.loginHandler.requestLoginView();
        mainGUI.setUser();
        mainGUI.setVisible(true);
    }
    
    private void firstStart() {
        testData();
        if(userHandler.usersIsEmpty()) {
            JOptionPane.showMessageDialog(loginDIA, "Der er ingen administrator. Du skal nu lave en...", "Fejl", JOptionPane.ERROR_MESSAGE);
            User u = new User("test@test.dk", "test", "test", "test", "test", "test", true, false);
            userRegister.add(u);
            loginHandler.checkUser("test@test.dk", "test");
            createUserDIA.setCreateAdmin();
            createUserDIA.setVisible(true);
            userRegister.remove(u);
        }
    }
    
    private void testData() {
        userHandler.createUser("test", "test", "test", "test", "test", "test", true, false);
    }

}
