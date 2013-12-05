/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.User;
import model.UserRegister;
import view.LoginUserGUI;
import view.MainGUI;

/**
 *
 * @author patrick
 */
public class LoginHandler implements HismHandlerIF {

    public static User loggedIn;
    private LoginUserGUI loginUserGUI;
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

    public void requestLogin() {
        loginUserGUI.setVisible(true);
    }
}
