/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.user;

import date.ADate;
import db.user.UserDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author patrick
 */
public class UserHandler {
    
    private UserRegister userRegister;
    private UserDAO userDAO;
    
    public UserHandler(UserRegister userRegister, UserDAO userDAO) {
        this.userRegister = userRegister;
        this.userDAO = userDAO;
    }
    
    public void populateUsersFromDB() {
        try {
            ArrayList<User> users = userDAO.getAllUsers();
            if(!users.isEmpty()) {
                userRegister.setUsers(users);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean usersIsEmpty() {
        return userRegister.isEmpty();
    }
    
    public void createUser(String email, String password, String firstname, String middlename, String lastname, ADate creationDate, boolean administrator, boolean reserve) {
        User u = new User(email, password, firstname, middlename, lastname, creationDate, administrator, reserve);
        userRegister.add(u);
        u.setId(userRegister.indexOf(u));
        try {
            userDAO.insertUser(u);
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public User checkUser(String email, String password) {
        return userRegister.checkUser(email, password);
    }
    
    public boolean isEmailFree(String email) {
        return userRegister.isEmailFree(email);
    }
    
}
