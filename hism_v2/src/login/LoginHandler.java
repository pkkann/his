/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import control.user.UserHandler;
import model.User;
import view.LoginDIA;

/**
 *
 * @author patrick
 */
public class LoginHandler {
    
    private User loggedInUser;
    private LoginDIA loginDIA;
    private UserHandler userHandler;
    
    public LoginHandler(LoginDIA loginDIA, UserHandler userHandler) {
        this.loginDIA = loginDIA;
        this.userHandler = userHandler;
    }
    
    public void requestLoginView() {
        if(loginDIA != null) {
            loginDIA.setVisible(true);
        }
    }
    
    public void setLoggedInUser(User user) {
        loggedInUser = user;
    }
    
}
