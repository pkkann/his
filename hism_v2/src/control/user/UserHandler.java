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
    
    public int countAdmins() {
        int count = 0;
        for(User u : userRegister.getUsers()) {
            if(u.isAdministrator()) {
                count++;
            }
        }
        return count;
    }
    
    public void createUser(String email, String password, String firstname, String middlename, String lastname, ADate creationDate, boolean administrator, boolean reserve) {
        User u = new User(email, password, firstname, middlename, lastname, creationDate, administrator, reserve);
        userRegister.add(u);
        int id = 0;
        try {
            id = userDAO.insertUser(u);
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        u.setId(id);
    }
    
    public User checkUser(String email, String password) {
        return userRegister.checkUser(email, password);
    }
    
    public boolean isEmailFree(String email) {
        return userRegister.isEmailFree(email);
    }
    
    public ArrayList<User> getAllUsers() {
        return userRegister.getUsers();
    }
    
    public User getUser(int id) {
        for(User u : userRegister.getUsers()) {
            if(u.getId() == id) {
                return u;
            }
        }
        return null;
    }
    
    public void removeUser(User u) {
        userRegister.remove(u);
        try {
            userDAO.deleteUser(u);
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveUser(User u) {
        userRegister.set(userRegister.indexOf(u), u);
        try {
            userDAO.saveUser(u);
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
