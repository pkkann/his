/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import control.user.UserHandler;
import model.User;
import view.LoginDIA;
import view.MainGUI;

/**
 *
 * @author patrick
 */
public class LoginHandler {

    private User loggedInUser;
    private LoginDIA loginDIA;
    private UserHandler userHandler;
    private MainGUI mainGUI;

    public LoginHandler(LoginDIA loginDIA, UserHandler userHandler, MainGUI mainGUI) {
        this.loginDIA = loginDIA;
        this.userHandler = userHandler;
        this.mainGUI = mainGUI;
        this.loginDIA = new LoginDIA(mainGUI, true, this);
    }

    public void requestLoginView() {
        loginDIA.setVisible(true);
    }
    
    public User getLoggedInUser() {
        return loggedInUser;
    }

    public boolean checkUser(String email, String password) {
        User u = userHandler.checkUser(email, password);
        if (u != null) {
            loggedInUser = u;
            return true;
        } else {
            return false;
        }
    }
}
