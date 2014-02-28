/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.User;
import javax.swing.JFrame;
import model.UserRegister;
import view.LoginUserGUI;
import view.LoginUserPassOnlyDIA;
import view.MainGUI;

/**
 *
 * @author patrick
 */
public class LoginHandler implements HismHandlerIF {

    public static User loggedIn;
    private LoginUserGUI loginUserGUI;
    private LoginUserPassOnlyDIA loginUserPassOnlyDIA;
    private UserRegister usR;
    private MainGUI mainGUI;

    public LoginHandler(UserRegister usR, MainGUI mainGUI) {
        this.usR = usR;
        loginUserGUI = new LoginUserGUI(this);
        this.mainGUI = mainGUI;
        mainGUI.setLoginHandler(this);
    }

    public int login(String username, String password) {

        for (User u : usR.getUsers()) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                System.out.println("USERNAME CORRECT");
                if (u.getPassword().equals(password)) {
                    System.out.println("PASSWORD CORRECT");
                    loggedIn = u;
                    mainGUI.setloggedInUser();
                    mainGUI.updateDate("");
                    mainGUI.updateEnrolledCounter();
                    mainGUI.setVisible(true);
                    return NO_ERROR;
                }
            }
        }

        return LOGIN_FAILED;
    }
    
    public int confirmPassword(String password) {
        if(loggedIn != null) {
            if(loggedIn.getPassword().equals(password)) {
                return PASSWORD_CORRECT;
            }
        }

        return PASSWORD_NOT_CONFIRMED;
    }

    public void requestLogin() {
        loginUserGUI.setVisible(true);
    }
    
    public int requestLoginOnlyPass(String title, String confirmButton, JFrame parent) {
        loginUserPassOnlyDIA = new LoginUserPassOnlyDIA(parent, true, this, title, confirmButton);
        
        loginUserPassOnlyDIA.setVisible(true);
        
        int returnCode = loginUserPassOnlyDIA.getReturnCode();
        
        loginUserPassOnlyDIA = null;
        
        return returnCode;
    }
}
