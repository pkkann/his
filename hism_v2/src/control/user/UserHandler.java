/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.user;

import model.User;

/**
 *
 * @author patrick
 */
public class UserHandler {
    
    private UserRegister userRegister;
    
    public UserHandler() {
        userRegister = new UserRegister();
    }
    
    public void createUser(String email, String password, String firstname, String middlename, String lastname, String creationDate, boolean administrator, boolean reserve) {
        User u = new User(email, password, firstname, middlename, lastname, creationDate, administrator, reserve);
        userRegister.add(u);
        u.setId(userRegister.indexOf(u));
    }
    
    public User checkUser(String email, String password) {
        return userRegister.checkUser(email, password);
    }
    
}
